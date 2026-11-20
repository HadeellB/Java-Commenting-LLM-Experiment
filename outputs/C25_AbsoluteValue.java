package com.thealgorithms.maths;

public final class AbsoluteValue {
    // Prevents instantiation of this utility class.
    private AbsoluteValue() {
    }

    /**
     * Returns the absolute value of the given number.
     * Long.MIN_VALUE remains negative because its positive magnitude
     * cannot be represented as a long.
     */
    public static long getAbsValue(long number) {
        return Math.abs(number);
    }
}