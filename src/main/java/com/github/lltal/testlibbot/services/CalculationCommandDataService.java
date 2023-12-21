package com.github.lltal.testlibbot.services;


import com.github.lltal.testlibbot.model.domain.CalculateCommandData;
import com.github.lltal.testlibbot.model.domain.UserData;

/**
 * Организует взаимодействие с бд при обработке {@link CalculateCommandData}
 */
public interface CalculationCommandDataService {

    /**
     * Сохранить данные пользователя для вычислений по id пользователя
     *
     * @param userId идентификатор пользователя
     * @param data данные пользователя для вычислений
     */
    void saveData(Long userId, CalculateCommandData data);
}
