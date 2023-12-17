package com.github.lltal.testlibbot.utils;

import lombok.Getter;

@Getter
public enum Action {
    MULTIPLY((n1, n2) -> n1 * n2, "multiply", "*"),
    DIVISION((n1, n2) -> n1 / n2, "division", "/"),
    INCREMENT((n1, n2) -> n1 + n2, "increment", "+"),
    DECREMENT((n1, n2) -> n1 - n2, "decrement", "-");

    private final CalculateFunction<Double, Double> calculateFunction;
    private final String name;
    private final String UserView;

    private Action(
            CalculateFunction<Double, Double> calculateFunction,
            String name,
            String userView){
        this.calculateFunction = calculateFunction;
        this.name = name;
        UserView = userView;
    }

}
