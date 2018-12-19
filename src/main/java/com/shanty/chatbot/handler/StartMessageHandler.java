package com.shanty.chatbot.handler;

import com.shanty.chatbot.ShantyKeyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartMessageHandler implements MessageHandler {
    @Override
    public String getHandlingMessage() {
        return "/start";
    }

    @Override
    public SendMessage handle(Update update) {
        Message updateMessage = update.getMessage();
        return new SendMessage()
                .setChatId(updateMessage.getChatId())
                .setText("Привет. Я твой помощник по комфортному проживанию в Шанти вилле. Чем могу помочь?")
                .setReplyMarkup(new ShantyKeyboard());
    }
}
