package com.shanty.chatbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ShantyBot extends TelegramLongPollingBot {
    private static String token;

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

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) {
            return;
        }

        Message updateMessage = update.getMessage();
        if (!updateMessage.hasText()) {
            return;
        }

        String messageText = updateMessage.getText();
        long chatId = updateMessage.getChatId();
        SendMessage message = new SendMessage().setChatId(chatId);
        switch (messageText) {
            case "/start":
                message.setText("Привет. Я твой помощник по комфортному проживанию в Шанти вилле. Чем могу помочь?");
                ReplyKeyboardMarkup keyboardMarkup = new ShantyKeyboard();
                message.setReplyMarkup(keyboardMarkup);
                break;

            /*case "/hideMarkup":
                message.setText("Keyboard are hidden now");
                message.setReplyMarkup(new ReplyKeyboardRemove());
                break;*/
            case "FAQ":
                message.setText("https://telegra.ph/FAQ-poleznye-ssylki-10-25");
                break;
            case "About":
                message.setText("https://telegra.ph/SHANTI-VILLA-10-26");
                break;
            case "Help":
                message.setText("Когда нибудь я обязательно помогу тебе! Но не сегодня...");
                break;
            /*case "Привет": {
                SendSticker sticker = new SendSticker();
                sticker.
                break;
            }*/

            default:
                message.setText("Unknown command: " + messageText);
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
