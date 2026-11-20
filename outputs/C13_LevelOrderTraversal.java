package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class LevelOrderTraversal {
    // Prevents instantiation of this utility class.
    private LevelOrderTraversal() {
    }

    // Returns node values grouped by depth, visiting each level from left to right.
    public static List<List<Integer>> traverse(BinaryTree.Node root) {
        if (root == null) {
            return List.of();
        }

        List<List<Integer>> result = new ArrayList<>();

        // A FIFO queue processes nodes in breadth-first order.
        Queue<BinaryTree.Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            // Capture the current level's size before adding children for the next level.
            int nodesOnLevel = q.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < nodesOnLevel; i++) {
                BinaryTree.Node tempNode = q.poll();
                level.add(tempNode.data);

                // Enqueue the left child before the right to preserve left-to-right order.
                if (tempNode.left != null) {
                    q.add(tempNode.left);
                }

                if (tempNode.right != null) {
                    q.add(tempNode.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    // Prints values at the requested level, counting the supplied root as level 1.
    public static void printGivenLevel(BinaryTree.Node root, int level) {
        // This check also applies to null children reached during recursion.
        if (root == null) {
            System.out.println("Root node must not be null! Exiting.");
            return;
        }
        if (level == 1) {
            System.out.print(root.data + " ");
        } else if (level > 1) {
            // Descend both subtrees, reducing the remaining distance to the target level.
            printGivenLevel(root.left, level - 1);
            printGivenLevel(root.right, level - 1);
        }
    }
}