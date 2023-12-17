package com.github.lltal.testlibbot.commands;

import com.github.lltal.testlibbot.domain.User;
import com.github.lltal.testlibbot.repository.impl.CalculateDataRepo;
import com.github.lltal.testlibbot.repository.impl.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.expression.spel.ast.OpAnd;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

import java.util.List;
import java.util.Optional;

@CommandNames("/calculate")
@AllArgsConstructor
public class CalculateCommand {
    @NonNull
    private final UserRepo userRepo;
    @NonNull
    private final CalculateDataRepo dataRepo;
    private final List<String> messagesText = List.of(
            "введи имя",
            "введи возраст",
            "введи вес");
    @CommandFirst
    public void execCalculate(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            @ParamName("userId") Long userId
    ) {
        Optional<User> candidateForProcessing = userRepo.find(userId);
        if (!candidateForProcessing.isPresent()) {

        }
    }
}
