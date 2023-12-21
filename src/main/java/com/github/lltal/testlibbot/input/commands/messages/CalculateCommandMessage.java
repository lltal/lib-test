package com.github.lltal.testlibbot.input.commands.messages;

import lombok.Getter;

@Getter
public enum CalculateCommandMessage {
    FIRST_NUMBER("Введи первое число"),
    SECOND_NUMBER("Введи второе число"),
    MATH_OPERATION("Выбери математическую операцию");

    private final String messageText;

    CalculateCommandMessage(String messageText){
        this.messageText = messageText;
    }
}
