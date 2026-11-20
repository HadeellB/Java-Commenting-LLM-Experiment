package com.thealgorithms.maths;

/**
 * Provides a square-root approximation using the Babylonian method.
 */
public final class SquareRootWithBabylonianMethod {
    // Prevents instantiation of this utility class.
    private SquareRootWithBabylonianMethod() {
    }

    public static float squareRoot(float num) {
        // Initialize the two values used in the iterative approximation.
        float a = num;
        float b = 1;
        double e = 0.000001;

        // Continue while a exceeds b by more than the tolerance.
        while (a - b > e) {
            // Average the current values to update the estimate.
            a = (a + b) / 2;

            // Calculate the complementary value using the updated estimate.
            b = num / a;
        }
        return a;
    }
}