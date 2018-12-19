package com.shanty.chatbot;

import com.shanty.chatbot.handler.FAQMessageHandler;
import com.shanty.chatbot.handler.HelpMessageHandler;
import com.shanty.chatbot.handler.RulesMessageHandler;
import com.shanty.chatbot.handler.StartMessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ShantyBot extends TelegramLongPollingBot {
    private static String token;
    private static MessageGateway gateway = new MessageGateway();

    static {
        InputStream tokenStream = ShantyBot.class.getClassLoader().getResourceAsStream("_token");
        if (tokenStream == null) {
            throw new IllegalArgumentException("Cannot find _token file");
        }

        try (BufferedReader is = new BufferedReader(new InputStreamReader(tokenStream))) {
            token = is.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }

        gateway.registerHandler(new StartMessageHandler());
        gateway.registerHandler(new FAQMessageHandler());
        gateway.registerHandler(new RulesMessageHandler());
        gateway.registerHandler(new HelpMessageHandler());
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) {
            return;
        }


        SendMessage message = gateway.routeMessage(update);
        if (message == null) {
            return;
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "ShantyBot";
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
