package com.github.lltal.testlibbot.services;


import com.github.lltal.testlibbot.model.domain.CalculateCommandData;

public interface CalculationCommandDataService {

    /**
     * Получить данные пользователя для вычислений по id пользователя
     *
     * @param userId идентификатор польлзователя
     */
    CalculateCommandData findDataByUserId(Long userId);

    /**
     * Сохранить данные пользователя для вычислений по id пользователя
     *
     * @param userId идентификатор пользователя
     * @param data данные пользователя для вычислений
     */
    void saveData(Long userId, CalculateCommandData data);
}
