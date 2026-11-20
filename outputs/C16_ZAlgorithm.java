/*
 * https://en.wikipedia.org/wiki/Z-algorithm
 */
package com.thealgorithms.strings;

public final class ZAlgorithm {

    // Prevents instantiation of this utility class.
    private ZAlgorithm() {
        throw new UnsupportedOperationException("Utility class");
    }

    // Computes the length of the prefix match starting at each position; z[0] remains 0.
    public static int[] zFunction(String s) {
        int n = s.length();
        int[] z = new int[n];

        // Inclusive boundaries of the rightmost known interval matching a prefix of s.
        int l = 0;
        int r = 0;

        for (int i = 1; i < n; i++) {
            if (i <= r) {
                // Reuses a previous match, limited to the current interval's boundary.
                z[i] = Math.min(r - i + 1, z[i - l]);
            }

            // Extends the match by comparing characters with the prefix.
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }

            // Updates the interval when this match reaches farther to the right.
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }

        return z;
    }

    public static int search(String text, String pattern) {
        // Separates the pattern from the text with "$"; this assumes "$" occurs in neither input.
        String s = pattern + "$" + text;
        int[] z = zFunction(s);
        int p = pattern.length();

        for (int i = 0; i < z.length; i++) {
            if (z[i] == p) {
                // Converts the combined-string position into a text index.
                return i - p - 1;
            }
        }

        // No Z value exactly matched the pattern length.
        return -1;
    }
}