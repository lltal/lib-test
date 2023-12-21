package com.github.lltal.testlibbot.services.impl;

import com.github.lltal.testlibbot.model.domain.UserData;
import com.github.lltal.testlibbot.output.repository.impl.UserDataRepo;
import com.github.lltal.testlibbot.services.UserDataService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class UserDataServiceImpl implements UserDataService {

    private final UserDataRepo userRepo;

    public void save(Long userId, UserData user){
        userRepo.save(userId, user);
    }
}
