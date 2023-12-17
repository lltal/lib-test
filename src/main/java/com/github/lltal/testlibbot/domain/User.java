package com.github.lltal.testlibbot.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
@Table(name = "usr")
public class User {
    @NonNull
    @Id
    private final Long id;
    private String name;
    private int age;
    private double weight;
    private int filledFieldCounter;
}
