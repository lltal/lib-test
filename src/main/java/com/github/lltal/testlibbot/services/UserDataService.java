package com.github.lltal.testlibbot.services;

import com.github.lltal.testlibbot.model.domain.UserData;

/**
 * Организует взаимодействие с бд при обработке {@link UserData}
 */
public interface UserDataService {

    /**
     * Сохранить данные о пользователе по id пользователя
     * 
     * @param userId идентификатор пользователя
     * @param userData данные о пользователе
     */
    void save(Long userId, UserData userData);
}
