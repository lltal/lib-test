package com.github.lltal.testlibbot.domain;

import com.github.lltal.testlibbot.utils.Action;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Table(name = "calculation_data")
public class CalculateCommandData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double firstNumber;
    private double secondNumber;
    @Enumerated
    private Action action;
    private int filledFieldCounter;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @NonNull
    private User user;
}
