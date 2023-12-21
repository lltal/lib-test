package com.github.lltal.testlibbot.model.domain;

import com.github.lltal.testlibbot.input.commands.dto.MyInfoDto;
import lombok.Data;

@Data
public final class UserData{
    private final int age;
    private final String name;
    private final double weight;

    public UserData(MyInfoDto myInfoDto){
        this.age = myInfoDto.getAge();
        this.name = myInfoDto.getName();
        this.weight = myInfoDto.getWeight();
    }
}
