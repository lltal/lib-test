package com.github.lltal.testlibbot.repository.impl;

import com.github.lltal.testlibbot.domain.CalculateData;
import com.github.lltal.testlibbot.repository.Repository;

import java.util.Optional;

public class CalculateDataRepo implements Repository<CalculateData, Long> {
    @Override
    public Optional<Long> find(CalculateData key) {
        return Optional.empty();
    }

    @Override
    public void save(Long value) {

    }
}
