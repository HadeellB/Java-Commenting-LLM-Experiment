package com.thealgorithms.strings;

public final class RemoveStars {

    private RemoveStars() {
    }

    /**
     * Removes each star and the most recent remaining character before it.
     * Stars with no preceding remaining character are ignored.
     */
    public static String removeStars(String s) {
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '*') {
                // Delete the last accumulated character, if one exists.
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else {
                // Keep non-star characters unless a later star removes them.
                result.append(c);
            }
        }
        return result.toString();
    }
}