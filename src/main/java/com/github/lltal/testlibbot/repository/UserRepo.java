package com.github.lltal.testlibbot.repository;

import com.github.lltal.testlibbot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
