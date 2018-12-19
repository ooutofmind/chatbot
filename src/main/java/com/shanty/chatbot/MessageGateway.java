package com.shanty.chatbot;

import com.shanty.chatbot.handler.MessageHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public class MessageGateway {
    private Map<String, MessageHandler> handlersRegistry = new HashMap<>();

    public void registerHandler(MessageHandler handler) {
        handlersRegistry.put(handler.getHandlingMessage(), handler);
    }

    public SendMessage routeMessage(Update update) {
        Message updateMessage = update.getMessage();
        if (!updateMessage.hasText()) {
            return null;
        }

        String text = updateMessage.getText();
        MessageHandler handler = handlersRegistry.get(text);
        if (handler != null) {
            return handler.handle(update);
        } else {
            return new SendMessage().setChatId(updateMessage.getChatId()).setText("Unsupported yet operation");
        }
    }
}
