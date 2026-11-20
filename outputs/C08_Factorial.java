package com.thealgorithms.maths;

import java.math.BigInteger;

public final class Factorial {
    // Prevents instantiation of this utility class.
    private Factorial() {
    }

    // Computes n! using BigInteger to support results beyond primitive integer ranges.
    public static BigInteger factorial(int n) {
        // Factorial is not defined here for negative inputs.
        if (n < 0) {
            throw new IllegalArgumentException("Input number cannot be negative");
        }

        // Starting at 1 also gives the correct result for 0!, since the loop is skipped.
        BigInteger result = BigInteger.ONE;

        // Accumulates the product of the integers from 1 through n.
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}