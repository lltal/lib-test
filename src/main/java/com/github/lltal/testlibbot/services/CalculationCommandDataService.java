package com.github.lltal.testlibbot.services;

import com.github.lltal.testlibbot.domain.CalculateCommandData;
import com.github.lltal.testlibbot.repository.CalculateCommandDataRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CalculationCommandDataService {
    private final CalculateCommandDataRepo dataRepo;

    public void saveData(CalculateCommandData data){
        dataRepo.save(data);
    }

    public CalculateCommandData findData(Long userId){
        return dataRepo.findDataByUserId(userId);
    }

}
