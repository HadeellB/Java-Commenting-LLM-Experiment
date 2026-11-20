package com.thealgorithms.maths.Prime;

/*
 * Authors:
 * (1) Aitor Fidalgo Sánchez (https://github.com/aitorfi)
 * (2) Akshay Dubey (https://github.com/itsAkshayDubey)
 */

import java.util.ArrayList;
import java.util.List;

public final class PrimeFactorization {
    // Prevents instantiation of this utility class.
    private PrimeFactorization() {
    }

    // Returns the prime factors of a positive integer, including repeated factors.
    public static List<Integer> pfactors(int n) {
        List<Integer> primeFactors = new ArrayList<>();

        // Handles zero separately to avoid an endless loop when dividing by 2.
        if (n == 0) {
            return primeFactors;
        }

        // Extracts every factor of 2, leaving an odd remainder.
        while (n % 2 == 0) {
            primeFactors.add(2);
            n /= 2;
        }

        // Tests odd divisors up to the square root of the remaining value.
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            // Records each occurrence of the divisor and reduces the remainder.
            while (n % i == 0) {
                primeFactors.add(i);
                n /= i;
            }
        }

        // Any remaining value greater than 2 is a prime factor.
        if (n > 2) {
            primeFactors.add(n);
        }
        return primeFactors;
    }
}