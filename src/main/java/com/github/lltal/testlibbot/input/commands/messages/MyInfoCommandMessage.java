package com.github.lltal.testlibbot.input.commands.messages;

import lombok.Getter;

@Getter
public enum MyInfoCommandMessage {
    NAME("Введи имя"),
    AGE("Введи возраст"),
    WEIGHT("Введи вес");

    private final String messageText;

    MyInfoCommandMessage(String messageText){
        this.messageText = messageText;
    }
}