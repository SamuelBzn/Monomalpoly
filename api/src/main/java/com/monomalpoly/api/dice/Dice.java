package com.monomalpoly.api.dice;

public class Dice {

    private final boolean isDouble;
    private final int value;
    private final String message;

    public Dice(int value, boolean isDouble, String message) {
        this.value = value;
        this.isDouble = isDouble;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public boolean getIsDouble() {
        return isDouble;
    }

    public String getMessage() {
        return message;
    }
}
