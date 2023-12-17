package com.github.lltal.testlibbot.input.callbacks;

import com.github.lltal.testlibbot.model.domain.CalculateCommandData;
import com.github.lltal.testlibbot.services.CalculationCommandDataService;
import com.github.lltal.testlibbot.utils.Action;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;
import ru.wdeath.telegram.bot.starter.command.CommandContext;
import ru.wdeath.telegram.bot.starter.command.TypeCommand;

@CommandNames(value = Action.MULTIPLY, type = TypeCommand.CALLBACK)
@RequiredArgsConstructor
@Component
public class MultiplyCallback {
    @NonNull
    private final CalculationCommandDataService dataService;
    
    @CommandFirst
    public void execMultiply(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            @ParamName("userId") Long userId
    ){
        CalculateCommandData data = dataService.findDataByUserId(userId);
        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(context.getUpdate().getCallbackQuery().getId()).build();

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(String.format("результат: %.2f", data.getFirstNumber() * data.getSecondNumber()))
                .build();

        context.getEngine().executeNotException(close);
        context.getEngine().executeNotException(sendMessage);
    }
}
