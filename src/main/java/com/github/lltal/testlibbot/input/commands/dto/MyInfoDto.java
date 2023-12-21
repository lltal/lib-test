package com.github.lltal.testlibbot.input.commands.dto;

import com.github.lltal.testlibbot.input.commands.messages.MyInfoCommandMessage;
import lombok.Data;

@Data
public final class MyInfoDto {
    private String name;
    private int age;
    private double weight;
    private MyInfoCommandMessage currentMessage = MyInfoCommandMessage.NAME;
}
