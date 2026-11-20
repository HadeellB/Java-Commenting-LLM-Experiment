package com.thealgorithms.bitmanipulation;

/**
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public final class IsEven {
    private IsEven() {
    }
    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }
}
