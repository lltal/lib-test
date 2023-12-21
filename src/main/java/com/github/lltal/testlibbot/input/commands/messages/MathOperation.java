package com.github.lltal.testlibbot.input.commands.messages;

public final class MathOperation {
    public static final String MULTIPLY = "*";
    public static final String DIVISION = "/";
    public static final String INCREMENT = "+";
    public static final String DECREMENT = "-";
    private static final String[] actions = new String[]{MULTIPLY, DIVISION, INCREMENT, DECREMENT};

    public static String[] getAllActions(){
        return actions;
    }

}
