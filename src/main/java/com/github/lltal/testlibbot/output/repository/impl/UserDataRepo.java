package com.github.lltal.testlibbot.output.repository.impl;

import com.github.lltal.testlibbot.model.domain.UserData;
import com.github.lltal.testlibbot.output.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserDataRepo implements Repository<Long, UserData> {
    private final Map<Long, UserData> userRepo = new ConcurrentHashMap<>();

    @Override
    public UserData save(Long key, UserData value) {
        return userRepo.put(key, value);
    }

    @Override
    public UserData get(Long key) {
        return userRepo.get(key);
    }
}
