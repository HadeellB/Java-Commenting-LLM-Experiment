package com.thealgorithms.bitmanipulation;

/**
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public final class IsEven {
    // Prevents instantiation of this utility class.
    private IsEven() {
    }

    /**
     * Returns true if the number is even, including zero and negative even numbers.
     */
    public static boolean isEven(int number) {
        // Mask all bits except the least significant bit, which is 0 for even numbers.
        return (number & 1) == 0;
    }
}