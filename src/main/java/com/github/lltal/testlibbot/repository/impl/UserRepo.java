package com.github.lltal.testlibbot.repository.impl;

import com.github.lltal.testlibbot.domain.User;
import com.github.lltal.testlibbot.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepo implements Repository<Long, User> {

    private final Map<Long, User> repo = new ConcurrentHashMap<>();

    @Override
    public Optional<User> find(Long key) {
        //remove, чтобы исключить конкурентность при изменении объекта
        return Optional.ofNullable(repo.get(key));
    }

    @Override
    public void save(User user) {
        repo.put(user.getId(), user);
    }
}
