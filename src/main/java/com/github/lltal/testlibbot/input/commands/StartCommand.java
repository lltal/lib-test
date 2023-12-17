package com.github.lltal.testlibbot.input.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

@CommandNames("/start")
@Component
public class StartCommand {

    @CommandFirst
    public void execStart(
            CommandContext context,
            @ParamName("chatId") Long chatId
    ) {
        String text = "Привет, посчитать - /calculate";
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();

        context.getEngine().executeNotException(sendMessage);
    }
}

