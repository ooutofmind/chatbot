package com.shanty.chatbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ShantyBot extends TelegramLongPollingBot {
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

        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(messageText);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "ShantyBot";
    }

    public String getBotToken() {
        return "743975544:AAHWS_WQXJlBX8XWXOSJx8QCuRU_xqrW1mg";
    }
}
