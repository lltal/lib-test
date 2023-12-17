package com.github.lltal.testlibbot.services;

import com.github.lltal.testlibbot.model.domain.UserData;
import com.github.lltal.testlibbot.output.repository.impl.UserDataRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserDataRepo userRepo;

    public UserData findUser(Long userId){
        return userRepo.get(userId);
    }

    public void save(Long userId, UserData user){
        userRepo.save(userId, user);
    }
}
