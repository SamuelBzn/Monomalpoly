package com.monomalpoly.api.dice;

import java.util.Random;

public class Dice {

    private final boolean isDouble;
    private final int value;
    private final String message;

    public static Dice draw() {
        int x = new Random().nextInt(6-1) + 1;
        int y = new Random().nextInt(6-1) + 1;

        if (x == y)
            return new Dice(x+y, true, "Vous avez fait " + (x+y) + " avec un double.");
        else
            return new Dice(x+y, false, "Vous avez fait " + (x+y) + ".");
    }

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
