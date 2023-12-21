package com.github.lltal.testlibbot.output.repository.impl;

import com.github.lltal.testlibbot.model.domain.CalculateCommandData;
import com.github.lltal.testlibbot.output.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public final class CalculationDataRepo implements Repository<Long, CalculateCommandData> {
    private final Map<Long, CalculateCommandData> dataRepo = new ConcurrentHashMap<>();

    @Override
    public CalculateCommandData save(Long key, CalculateCommandData value) {
        return dataRepo.put(key, value);
    }
}
