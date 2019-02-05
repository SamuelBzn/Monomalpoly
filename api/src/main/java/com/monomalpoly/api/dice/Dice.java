package com.monomalpoly.api.dice;

public class Dice {

    private final boolean isDouble;
    private final long value;
    private final String message;

    public Dice(long value, boolean isDouble, String message) {
        this.value = value;
        this.isDouble = isDouble;
        this.message = message;
    }

    public long getValue() {
        return value;
    }

    public boolean getIsDouble() {
        return isDouble;
    }

    public String getMessage() {
        return message;
    }
}
