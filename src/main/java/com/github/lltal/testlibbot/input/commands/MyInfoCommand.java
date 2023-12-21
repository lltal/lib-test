package com.github.lltal.testlibbot.input.commands;

import com.github.lltal.testlibbot.input.commands.dto.MyInfoDto;
import com.github.lltal.testlibbot.input.commands.messages.MyInfoCommandMessage;
import com.github.lltal.testlibbot.model.domain.UserData;
import com.github.lltal.testlibbot.services.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.CommandOther;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;
import ru.wdeath.telegram.bot.starter.command.CommandContext;
import ru.wdeath.telegram.bot.starter.session.UserBotSession;

@CommandNames("/my_info")
@Component
@RequiredArgsConstructor
public final class MyInfoCommand {

    private final UserDataService userDataService;

    @CommandFirst
    public void execMyInfo(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            UserBotSession userBotSession
    ) {
        MyInfoDto myInfoDto = new MyInfoDto();
        userBotSession.setData(myInfoDto);
        send(myInfoDto, context.getEngine(), chatId);

    }

    @CommandOther
    public void other(
            CommandContext context,
            UserBotSession userSession,
            @ParamName("chatId") Long chatId,
            @ParamName("userId") Long userId,
            @ParamName("message") Message message
    ){
        MyInfoDto myInfoDto = (MyInfoDto) userSession.getData();
        TelegramLongPollingEngine engine = context.getEngine();
        switch (myInfoDto.getCurrentMessage()){
            case NAME -> {
                myInfoDto.setName(message.getText());
                myInfoDto.setCurrentMessage(MyInfoCommandMessage.AGE);
                send(myInfoDto, engine, chatId);

            }
            case AGE -> {
                myInfoDto.setAge(Integer.parseInt(message.getText()));
                myInfoDto.setCurrentMessage(MyInfoCommandMessage.WEIGHT);
                send(myInfoDto, engine, chatId);
            }
            case WEIGHT -> {
                myInfoDto.setWeight(Double.parseDouble(message.getText()));
                userDataService.save(userId, new UserData(myInfoDto));
                send(calculateRemainingAge(myInfoDto), engine, chatId);
            }
        }
    }

    private String calculateRemainingAge(MyInfoDto myInfoDto){
        return String.format("Тебе осталось жить %.2f лет", myInfoDto.getAge() * myInfoDto.getWeight() / 100);
    }

    private void send(MyInfoDto myInfoDto, TelegramLongPollingEngine engine, Long chatId){
        send(myInfoDto.getCurrentMessage().getMessageText(), engine, chatId);
    }

    private void send(String text, TelegramLongPollingEngine engine, Long chatId){
        engine.executeNotException(SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build());
    }
}
