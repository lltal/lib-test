package com.github.lltal.testlibbot.input.commands;

import com.github.lltal.testlibbot.input.commands.messages.MyInfoCommandMessage;
import com.github.lltal.testlibbot.input.dto.MyInfoDto;
import com.github.lltal.testlibbot.model.domain.UserData;
import com.github.lltal.testlibbot.services.UserDataService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.CommandOther;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

import java.util.List;

@CommandNames("/my_info")
@Component
public class MyInfoCommand {

    @CommandFirst
    public void execMyInfo(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            @ParamName("userId") Long userId
    ) {
        MyInfoDto myInfoDto = new MyInfoDto();

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text()
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
        UserData userData = userService.findUserData(userId);
        setValue(userData, text);

        userService.save(userId, userData);

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(provideNewText(userData))
                .build();

        context.getEngine().executeNotException(sendMessage);
    }

    private String provideNextMessage(MyInfoDto myInfoDto){
        switch (myInfoDto.getCurrentMessage()) {
            case NAME -> {
                return "Введи имя";
            }
            case AGE -> {
                return "Введи возраст";
            }
            case WEIGHT -> {
                return "Введи вес";
            }
        }
    }
}
