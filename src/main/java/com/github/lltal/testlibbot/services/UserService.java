package com.github.lltal.testlibbot.services;

import com.github.lltal.testlibbot.domain.User;
import com.github.lltal.testlibbot.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

@Service
@RequiredArgsConstructor
public class UserService {
    @NonNull
    private final UserRepo userRepo;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public User createIfAbsent(Long userId){
        return userRepo
                .findById(userId)
                .orElse(
                        userRepo.save(new User(userId)));
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void save(User user){
        userRepo.save(user);
    }

    @Lock(LockModeType.PESSIMISTIC_READ)
    public User findUser(Long userId){
        return userRepo
                .findById(userId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
