package com.github.lltal.testlibbot.input.commands;

import com.github.lltal.testlibbot.input.commands.dto.CalculateDto;
import com.github.lltal.testlibbot.input.commands.messages.CalculateCommandMessage;
import com.github.lltal.testlibbot.input.commands.messages.MathOperation;
import com.github.lltal.testlibbot.model.domain.CalculateCommandData;
import com.github.lltal.testlibbot.services.CalculationCommandDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.CommandOther;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;
import ru.wdeath.telegram.bot.starter.callback.CallbackData;
import ru.wdeath.telegram.bot.starter.callback.CallbackDataSender;
import ru.wdeath.telegram.bot.starter.command.CommandContext;
import ru.wdeath.telegram.bot.starter.session.UserBotSession;
import ru.wdeath.telegram.bot.starter.util.KeyboardUtil;

import java.util.Arrays;

@CommandNames("/calculate")
@RequiredArgsConstructor
@Component
public final class CalculateCommand {

    private final CalculationCommandDataService calculationService;
    private final CallbackDataSender[][] buttons = {
            Arrays.stream(MathOperation.getAllActions())
                    .map(action -> new CallbackDataSender(action, new CallbackData(action, "")))
                    .toArray(CallbackDataSender[]::new)};

    @CommandFirst
    public void execCalculate(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            UserBotSession userBotSession
    ) {
        CalculateDto calculateDto = new CalculateDto();
        userBotSession.setData(calculateDto);
        send(calculateDto, context.getEngine(), chatId);
    }

    @CommandOther
    public void other(
            CommandContext context,
            UserBotSession userSession,
            @ParamName("chatId") Long chatId,
            @ParamName("userId") Long userId,
            @ParamName("message") Message message
    ){
        CalculateDto calculateDto = (CalculateDto) userSession.getData();
        TelegramLongPollingEngine engine = context.getEngine();
        switch (calculateDto.getCurrentMessage()){
            case FIRST_NUMBER -> {
                calculateDto.setFirstNumber(Integer.parseInt(message.getText()));
                calculateDto.setCurrentMessage(CalculateCommandMessage.SECOND_NUMBER);
                send(calculateDto, engine, chatId);
            }
            case SECOND_NUMBER -> {
                calculateDto.setSecondNumber(Integer.parseInt(message.getText()));
                calculateDto.setCurrentMessage(CalculateCommandMessage.MATH_OPERATION);
                sendWithKeyBoard(calculateDto.getCurrentMessage().getMessageText(), engine, chatId);
            }
            case MATH_OPERATION -> {
                calculateResult(calculateDto, context.getName());
                calculationService.saveData(userId, new CalculateCommandData(calculateDto));
                closeCallback(context);
                send(
                        String.format("Результат: %.2f", calculateDto.getCalculationResult()),
                        engine, chatId);
            }
        }
    }

    private void calculateResult(CalculateDto calculateDto, String mathOperation){
        switch (mathOperation) {
            case MathOperation.MULTIPLY -> {
                calculateDto.setCalculationResult(calculateDto.getFirstNumber() * calculateDto.getSecondNumber());
            }
            case MathOperation.DIVISION -> {
                calculateDto.setCalculationResult(calculateDto.getFirstNumber() / calculateDto.getSecondNumber());
            }
            case MathOperation.INCREMENT -> {
                calculateDto.setCalculationResult(calculateDto.getFirstNumber() + calculateDto.getSecondNumber());
            }
            case MathOperation.DECREMENT -> {
                calculateDto.setCalculationResult(calculateDto.getFirstNumber() - calculateDto.getSecondNumber());
            }
        }
    }

    private void closeCallback(CommandContext context){
        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(context.getUpdate().getCallbackQuery().getId()).build();
        context.getEngine().executeNotException(close);
    }

    private void send(CalculateDto calculateDto, TelegramLongPollingEngine engine, Long chatId){
        send(calculateDto.getCurrentMessage().getMessageText(), engine, chatId);
    }

    private void send(String text, TelegramLongPollingEngine engine, Long chatId) {
        engine.executeNotException(SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build());
    }

    private void sendWithKeyBoard(String text, TelegramLongPollingEngine engine, Long chatId){
        engine.executeNotException(SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .replyMarkup(KeyboardUtil.inline(buttons))
                .build());
    }
}
