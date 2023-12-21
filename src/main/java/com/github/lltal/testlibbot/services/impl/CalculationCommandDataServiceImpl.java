package com.github.lltal.testlibbot.services.impl;

import com.github.lltal.testlibbot.model.domain.CalculateCommandData;
import com.github.lltal.testlibbot.output.repository.impl.CalculationDataRepo;
import com.github.lltal.testlibbot.services.CalculationCommandDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class CalculationCommandDataServiceImpl implements CalculationCommandDataService {

    private final CalculationDataRepo dataRepo;

    public void saveData(Long userId, CalculateCommandData data){
        dataRepo.save(userId, data);
    }
}
