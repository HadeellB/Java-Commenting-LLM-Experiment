package com.thealgorithms.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

// Provides three approaches for finding unique value triplets that sum to a target.
public class ThreeSumProblem {

    public List<List<Integer>> bruteForce(int[] nums, int target) {
        List<List<Integer>> arr = new ArrayList<List<Integer>>();

        // Examine every combination of three distinct indices.
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        // Normalize the order so equal value triplets can be deduplicated.
                        Collections.sort(temp);
                        arr.add(temp);
                    }
                }
            }
        }
        // Remove duplicate triplets while preserving their first occurrence order.
        arr = new ArrayList<List<Integer>>(new LinkedHashSet<List<Integer>>(arr));
        return arr;
    }

    public List<List<Integer>> twoPointer(int[] nums, int target) {
        // Sort the input array in place to enable directional pointer movement.
        Arrays.sort(nums);
        List<List<Integer>> arr = new ArrayList<List<Integer>>();
        int start = 0;
        int end = 0;
        int i = 0;
        // Fix one element and search for a matching pair among later elements.
        while (i < nums.length - 1) {
            start = i + 1;
            end = nums.length - 1;
            while (start < end) {
                if (nums[start] + nums[end] + nums[i] == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[start]);
                    temp.add(nums[end]);
                    arr.add(temp);
                    // Continue searching inward after recording a matching triplet.
                    start++;
                    end--;
                } else if (nums[start] + nums[end] + nums[i] < target) {
                    // Move toward a larger value to increase the sum.
                    start += 1;
                } else {
                    // Move toward a smaller value to decrease the sum.
                    end -= 1;
                }
            }
            i++;
        }
        // Sorted triplets allow duplicates to be removed using list equality.
        Set<List<Integer>> set = new LinkedHashSet<List<Integer>>(arr);
        return new ArrayList<List<Integer>>(set);
    }

    public List<List<Integer>> hashMap(int[] nums, int target) {
        // Sort in place so triplets selected by increasing indices have consistent ordering.
        Arrays.sort(nums);
        Set<List<Integer>> ts = new HashSet<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        // Store the last index of each value, replacing earlier indices for duplicates.
        for (int i = 0; i < nums.length; i++) {
            hm.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // Compute the third value needed to reach the target.
                int t = target - nums[i] - nums[j];
                // Require an occurrence after j to use three distinct indices.
                if (hm.containsKey(t) && hm.get(t) > j) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(t);
                    // The set retains only unique value triplets.
                    ts.add(temp);
                }
            }
        }
        return new ArrayList<>(ts);
    }
}