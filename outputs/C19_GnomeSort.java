package com.thealgorithms.sorts;

/**
 * Sorts an array in place using gnome sort, comparing adjacent elements
 * and moving backward after swaps.
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @since 2018-04-10
 */
public class GnomeSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(final T[] array) {
        // i tracks the current comparison; j stores where forward scanning resumes.
        int i = 1;
        int j = 2;
        while (i < array.length) {
            if (SortUtils.less(array[i - 1], array[i])) {
                // Resume forward scanning when the comparison succeeds.
                i = j++;
            } else {
                // Swap adjacent elements, then check the preceding pair.
                SortUtils.swap(array, i - 1, i);
                if (--i == 0) {
                    // At the beginning of the array, resume forward scanning.
                    i = j++;
                }
            }
        }

        // Return the same array after sorting it in place.
        return array;
    }
}