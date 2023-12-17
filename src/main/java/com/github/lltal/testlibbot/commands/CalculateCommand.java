package com.github.lltal.testlibbot.commands;

import com.github.lltal.testlibbot.domain.CalculateCommandData;
import com.github.lltal.testlibbot.domain.User;
import com.github.lltal.testlibbot.services.CalculationCommandDataService;
import com.github.lltal.testlibbot.services.UserService;
import com.github.lltal.testlibbot.utils.Action;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.CommandOther;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;
import ru.wdeath.telegram.bot.starter.callback.CallbackData;
import ru.wdeath.telegram.bot.starter.callback.CallbackDataSender;
import ru.wdeath.telegram.bot.starter.command.CommandContext;
import ru.wdeath.telegram.bot.starter.util.KeyboardUtil;

import java.util.Arrays;
import java.util.List;

@CommandNames("/calculate")
@AllArgsConstructor
public class CalculateCommand {
    @NonNull
    private final UserService userService;
    @NonNull
    private final CalculationCommandDataService dataService;

    private final CallbackDataSender[][] buttons = {
                Arrays.stream(Action.getAllActions())
                    .map(action -> new CallbackDataSender(action, new CallbackData(action, "")))
                    .toArray(CallbackDataSender[]::new)};

    private final List<String> messagesText = List.of(
            "введи первое число",
            "введи второе число",
            "выбери математическую операцию");

    private final int INDEX_OF_LAST_ELEMENT_IN_MESSAGES_TEXT = messagesText.size() - 1;

    @CommandFirst
    public void execCalculate(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            @ParamName("userId") Long userId
    ) {
        User user = userService.createIfAbsent(userId);
        CalculateCommandData commandData = new CalculateCommandData(user);
        dataService.saveData(commandData);

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(provideNewText(commandData))
                .build();

        context.getEngine().executeNotException(sendMessage);
    }

    @CommandOther
    public void other(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            @ParamName("userId") Long userId,
            @ParamName("message") Message message
    ){
        String text = message.getText();
        CalculateCommandData data = dataService.findData(userId);
        setValue(data, text);

        dataService.saveData(data);

        context.getEngine().executeNotException(provideNewSendMessage(data, chatId));
    }

    private SendMessage provideNewSendMessage(CalculateCommandData data, Long chatId){
        String text = provideNewText(data);
        return text.equals(messagesText.get(INDEX_OF_LAST_ELEMENT_IN_MESSAGES_TEXT))
                ?
                SendMessage.builder()
                        .chatId(chatId)
                        .text(provideNewText(data))
                        .replyMarkup(KeyboardUtil.inline(buttons))
                        .build()
                :
                SendMessage.builder()
                        .chatId(chatId)
                        .text(provideNewText(data))
                        .build();
    }

    private void setValue(CalculateCommandData data, String text) {
        int filledFieldCounter = data.getFilledFieldCounter();
        if (filledFieldCounter == 0) {
            data.setFirstNumber(Double.parseDouble(text));
        } else if (filledFieldCounter == 1) {
            data.setSecondNumber(Double.parseDouble(text));
        }
        data.setFilledFieldCounter(++filledFieldCounter);
    }

    private String provideNewText(CalculateCommandData data){
        return data.getFilledFieldCounter() < messagesText.size() ?
                messagesText.get(data.getFilledFieldCounter()) :
                "ошибка ввода";
    }
}
