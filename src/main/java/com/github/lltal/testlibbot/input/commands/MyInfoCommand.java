package com.github.lltal.testlibbot.input.commands;

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
@RequiredArgsConstructor
@Component
public class MyInfoCommand {

    @NonNull
    private final UserDataService userService;
    private final List<String> messagesText = List.of(
            "введи имя",
            "введи возраст",
            "введи вес");

    @CommandFirst
    public void execMyInfo(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            @ParamName("userId") Long userId
    ) {
        UserData userData = new UserData();
        userService.save(userId, userData);

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(provideNewText(userData))
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

    private void setValue(UserData userData, String text) {
        int filledFieldCounter = userData.getFilledFieldCounter();
        if (filledFieldCounter == 0) {
            userData.setName(text);
        } else if (filledFieldCounter == 1) {
            userData.setAge(Integer.parseInt(text));
        } else if (filledFieldCounter == 2) {
            userData.setWeight(Double.parseDouble(text));
        }
        userData.setFilledFieldCounter(++filledFieldCounter);
    }

    private String provideNewText(UserData userData){
        return userData.getFilledFieldCounter() < messagesText.size() ?
                messagesText.get(userData.getFilledFieldCounter()) :
                generateLastMessageText(userData);
    }

    private String generateLastMessageText(UserData userData) {
        return String.format("%s, тебе осталось жить %.2f лет",
                userData.getName(),
                (userData.getWeight() * userData.getAge()) / 100);
    }
}
