package com.thealgorithms.searches;

import com.thealgorithms.datastructures.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: caos321
 * @date: 31 October 2021 (Sunday)
 * @wiki: https://en.wikipedia.org/wiki/Depth-first_search
 */
public class DepthFirstSearch<T> {

    // Records visited node values and retains them across search calls.
    private final List<T> visited = new ArrayList<>();

    // Recursively searches the current node and its descendants for the given value.
    public Optional<Node<T>> recursiveSearch(final Node<T> node, final Integer value) {
        // A null node represents a search branch with no match.
        if (node == null) {
            return Optional.empty();
        }
        // Record the current value before checking for a match.
        visited.add(node.getValue());
        if (node.getValue().equals(value)) {
            return Optional.of(node);
        }

        // Search child branches depth-first, discard empty results, and return a match if found.
        return node.getChildren().stream().map(v -> recursiveSearch(v, value)).flatMap(Optional::stream).findAny();
    }

    // Returns the underlying list of recorded values, allowing callers to modify it.
    public List<T> getVisited() {
        return visited;
    }
}