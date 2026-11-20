package com.thealgorithms.strings;

import java.util.Arrays;
import java.util.Collections;

public final class ReverseWordsInString {

    private ReverseWordsInString() {
    }

    /**
     * Reverses the order of words, removing leading and trailing whitespace
     * and separating the resulting words with a single space.
     */
    public static String reverseWordsInString(final String s) {
        // Trim surrounding whitespace and split on one or more whitespace characters.
        var words = s.trim().split("\\s+");

        // The list is backed by the array, so reversing it also reorders words.
        Collections.reverse(Arrays.asList(words));

        return String.join(" ", words);
    }
}