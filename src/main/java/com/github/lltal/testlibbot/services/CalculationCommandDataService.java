package com.github.lltal.testlibbot.services;

import com.github.lltal.testlibbot.model.domain.CalculateCommandData;
import com.github.lltal.testlibbot.output.repository.impl.CalculationDataRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CalculationCommandDataService {

    private final CalculationDataRepo dataRepo;

    public CalculateCommandData findDataByUserId(Long userId){
        return dataRepo.get(userId);
    }

    public void saveData(long userId, CalculateCommandData data){
        dataRepo.save(userId, data);
    }
}
