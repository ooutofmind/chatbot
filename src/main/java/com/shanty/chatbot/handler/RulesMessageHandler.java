package com.shanty.chatbot.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RulesMessageHandler implements MessageHandler {
    @Override
    public String getHandlingMessage() {
        return "Rules";
    }

    @Override
    public SendMessage handle(Update update) {
        Message updateMessage = update.getMessage();
        String messageText = updateMessage.getText();
        long chatId = updateMessage.getChatId();
        return new SendMessage()
                .setChatId(chatId)
                .setText("https://telegra.ph/SHANTI-VILLA-10-26");
    }
}
