package com.thealgorithms.maths;

public final class StandardDeviation {
    // Prevents instantiation of this utility class.
    private StandardDeviation() {
    }

    // Computes the population standard deviation of the supplied values.
    public static double stdDev(double[] data) {
        double variance = 0;
        double avg = 0;

        // Calculate the arithmetic mean.
        for (int i = 0; i < data.length; i++) {
            avg += data[i];
        }
        avg /= data.length;

        // Sum the squared differences between each value and the mean.
        for (int j = 0; j < data.length; j++) {
            variance += Math.pow((data[j] - avg), 2);
        }

        // Divide by the population size to obtain the population variance.
        variance /= data.length;

        // Standard deviation is the square root of the variance.
        return Math.sqrt(variance);
    }
}