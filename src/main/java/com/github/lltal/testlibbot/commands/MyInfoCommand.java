package com.github.lltal.testlibbot.commands;

import com.github.lltal.testlibbot.domain.User;
import com.github.lltal.testlibbot.repository.UserRepo;
import com.github.lltal.testlibbot.services.UserService;
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
import java.util.Optional;

@CommandNames("/my_info")
@Component
@RequiredArgsConstructor
public class MyInfoCommand {

    @NonNull
    private final UserService userService;
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

        User user = userService.createIfAbsent(userId);

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(provideNewText(user))
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
        User user = userService.findUser(userId);
        setValue(user, text);

        userService.save(user);

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(provideNewText(user))
                .build();

        context.getEngine().executeNotException(sendMessage);
    }

    private void setValue(User user, String text) {
        int filledFieldCounter = user.getFilledFieldCounter();
        if (filledFieldCounter == 0) {
            user.setName(text);
        } else if (filledFieldCounter == 1) {
            user.setAge(Integer.parseInt(text));
        } else if (filledFieldCounter == 2) {
            user.setWeight(Double.parseDouble(text));
        }
        user.setFilledFieldCounter(++filledFieldCounter);
    }

    private String provideNewText(User user){
        return user.getFilledFieldCounter() < messagesText.size() ?
                messagesText.get(user.getFilledFieldCounter()) :
                generateLastMessageText(user);
    }

    private String generateLastMessageText(User user) {
        return String.format("%s, тебе осталось жить %.2f лет",
                user.getName(),
                (user.getWeight() * user.getAge()) / 100);
    }
}
