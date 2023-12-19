package com.github.lltal.testlibbot.model.domain;

import lombok.Data;
import ru.wdeath.managerbot.lib.db.UserTelegramEntity;

@Data
public class UserData{
    private String name;
    private int age;
    private double weight;
    private int filledFieldCounter;
    private UserTelegramEntity userTelegramEntity;
}
