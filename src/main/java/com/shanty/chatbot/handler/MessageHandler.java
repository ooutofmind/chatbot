package com.shanty.chatbot.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageHandler {
    String getHandlingMessage();

    SendMessage handle(Update update);
}
