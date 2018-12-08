package com.shanty.chatbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi api = new TelegramBotsApi();

        try {
            api.registerBot(new ShantyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
