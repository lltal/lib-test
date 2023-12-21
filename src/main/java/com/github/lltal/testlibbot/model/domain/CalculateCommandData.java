package com.github.lltal.testlibbot.model.domain;

import com.github.lltal.testlibbot.input.commands.dto.CalculateDto;
import lombok.Data;
import ru.wdeath.managerbot.lib.db.UserTelegramEntity;

@Data
public final class CalculateCommandData {
    private double firstNumber;
    private double secondNumber;
    private double calculationResult;

    public CalculateCommandData(CalculateDto calculateDto){
        this.firstNumber = calculateDto.getFirstNumber();
        this.secondNumber = calculateDto.getSecondNumber();
        this.calculationResult = calculateDto.getCalculationResult();
    }
}
