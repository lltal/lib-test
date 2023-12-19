package com.github.lltal.testlibbot.model.domain;

import lombok.Data;
import ru.wdeath.managerbot.lib.db.UserTelegramEntity;

@Data
public class CalculateCommandData {
    private double firstNumber;
    private double secondNumber;
    private int filledFieldCounter;
    private UserTelegramEntity userTelegramEntity;
}
