package com.github.lltal.testlibbot.input.commands.dto;

import com.github.lltal.testlibbot.input.commands.messages.CalculateCommandMessage;
import lombok.Data;

@Data
public final class CalculateDto {
    private double firstNumber;
    private double secondNumber;
    private Double calculationResult;
    private CalculateCommandMessage currentMessage = CalculateCommandMessage.FIRST_NUMBER;
}
