package com.thealgorithms.maths;

public final class ParseInteger {
    // Prevents instantiation of this utility class.
    private ParseInteger() {
    }

    // Rejects null and empty strings before their characters are accessed.
    private static void checkInput(final String s) {
        if (s == null) {
            throw new NumberFormatException("Input parameter must not be null!");
        }
        if (s.isEmpty()) {
            throw new NumberFormatException("Input parameter must not be empty!");
        }
    }

    // Checks whether the character at the given position is a digit.
    private static void checkDigitAt(final String s, final int pos) {
        if (!Character.isDigit(s.charAt(pos))) {
            throw new NumberFormatException("Input parameter of incorrect format: " + s);
        }
    }

    // Converts ASCII digits '0' through '9' to their integer values.
    private static int digitToInt(final char digit) {
        return digit - '0';
    }

    public static int parseInt(final String s) {
        checkInput(s);

        // Detects an optional leading sign.
        final boolean isNegative = s.charAt(0) == '-';
        final boolean isPositive = s.charAt(0) == '+';
        int number = 0;
        // Skips the sign, if present, and processes the remaining characters.
        for (int i = isNegative || isPositive ? 1 : 0, length = s.length(); i < length; ++i) {
            checkDigitAt(s, i);
            // Appends the digit to the accumulated value without checking for overflow.
            number = number * 10 + digitToInt(s.charAt(i));
        }
        // Applies the leading minus sign to the accumulated value.
        return isNegative ? -number : number;
    }
}