package com.github.lltal.testlibbot.input.dto;

import com.github.lltal.testlibbot.input.commands.messages.MyInfoCommandMessage;
import lombok.Data;

@Data
public class MyInfoDto {
    private String name;
    private int age;
    private double weight;
    private MyInfoCommandMessage currentMessage;
}
