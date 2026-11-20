package com.thealgorithms.maths;

public final class Floor {

    // Prevents instantiation of this utility class.
    private Floor() {
    }

    public static double floor(double number) {
        // Return the input unchanged if it equals its int conversion.
        if (number - (int) number == 0) {
            return number;
        } else if (number - (int) number > 0) {
            // Use the int conversion when it is less than the input.
            return (int) number;
        } else {
            // Subtract one when the input is below its int conversion.
            return (int) number - 1;
        }
    }
}