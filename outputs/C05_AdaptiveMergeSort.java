package com.thealgorithms.sorts;

// Sorts the input array using recursive merge sort and a shared auxiliary array.
public class AdaptiveMergeSort implements SortAlgorithm {
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length <= 1) {
            return array;
        }
        // Allocate a temporary array once for use by all merge operations.
        T[] aux = array.clone();
        sort(array, aux, 0, array.length - 1);
        return array;
    }

    // Sorts the inclusive range from low to high.
    private <T extends Comparable<T>> void sort(T[] array, T[] aux, int low, int high) {
        if (low >= high) {
            return;
        }
        // Sort both halves before merging them into a single sorted range.
        int mid = low + (high - low) / 2;
        sort(array, aux, low, mid);
        sort(array, aux, mid + 1, high);
        merge(array, aux, low, mid, high);
    }

    private <T extends Comparable<T>> void merge(T[] array, T[] aux, int low, int mid, int high) {
        // Preserve the range so writing merged values does not overwrite unread values.
        System.arraycopy(array, low, aux, low, high - low + 1);
        // Track the next unread element in each sorted half.
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                // The left half is exhausted; take from the right half.
                array[k] = aux[j++];
            } else if (j > high) {
                // The right half is exhausted; take from the left half.
                array[k] = aux[i++];
            } else if (SortUtils.less(aux[j], aux[i])) {
                array[k] = aux[j++];
            } else {
                // Choose the left element on ties to preserve their relative order.
                array[k] = aux[i++];
            }
        }
    }
}