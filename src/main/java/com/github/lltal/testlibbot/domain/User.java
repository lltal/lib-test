package com.github.lltal.testlibbot.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    @NonNull
    private final Long id;
    private String name;
    private int age;
    private double weight;
    private int filledFieldCounter;
}
