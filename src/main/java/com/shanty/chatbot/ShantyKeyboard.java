package com.shanty.chatbot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ShantyKeyboard extends ReplyKeyboardMarkup {
    public ShantyKeyboard() {

        Properties properties = new Properties();
        try (InputStream is = ShantyKeyboard.class.getClassLoader().getResourceAsStream("keyboard.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read keyboard.properties file", e);
        }

        List<KeyboardRow> keyboard = new ArrayList<>();
        Set<String> propertyNames = properties.stringPropertyNames();
        Integer lastRowProcessed = null;
        KeyboardRow currentRow = new KeyboardRow();
        keyboard.add(currentRow);
        for (String key : propertyNames) {
            String[] rowCellArr = key.split("\\.");
            Integer rowIndex = Integer.parseInt(rowCellArr[0].replace("row", ""));

            if (!rowIndex.equals(lastRowProcessed)) {
                lastRowProcessed = rowIndex;
                currentRow = new KeyboardRow();
                keyboard.add(currentRow);
            }
            currentRow.add(properties.getProperty(key));
        }

        setKeyboard(keyboard);
    }
}
