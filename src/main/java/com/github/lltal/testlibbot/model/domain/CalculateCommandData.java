package com.github.lltal.testlibbot.model.domain;

import lombok.Data;

@Data
public class CalculateCommandData {
    private double firstNumber;
    private double secondNumber;
    private int filledFieldCounter;
}
