package com.thealgorithms.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;


// Constructs a Gomory-Hu cut tree for an undirected capacity graph.
public final class GomoryHuTree {
    private GomoryHuTree() {
    }

    // Returns the tree's parent array and edge-weight array, in that order.
    // The undirected-graph interpretation requires symmetric capacities;
    // validation below checks only dimensions and non-negative entries.
    public static int[][] buildTree(int[][] cap) {
        validateCapacityMatrix(cap);
        final int n = cap.length;
        if (n == 1) {
            return new int[][] {new int[] {-1}, new int[] {0}};
        }

        // Initially attach every other vertex to vertex 0, the root.
        // weight[v] stores the weight of the edge from v to parent[v].
        int[] parent = new int[n];
        int[] weight = new int[n];
        Arrays.fill(parent, 0);
        parent[0] = -1;
        weight[0] = 0;

        for (int s = 1; s < n; s++) {
            // Compute a minimum cut between this vertex and its current parent.
            int t = parent[s];
            MaxFlowResult res = edmondsKarpWithMinCut(cap, s, t);
            int f = res.flow;
            weight[s] = f;

            // Move children of t on the source side of the cut under s.
            for (int v = 0; v < n; v++) {
                if (v != s && parent[v] == t && res.reachable[v]) {
                    parent[v] = s;
                }
            }

            // If t's parent is also on the source side, reverse the s-t
            // relationship and transfer the corresponding edge weights.
            if (t != 0 && res.reachable[parent[t]]) {
                parent[s] = parent[t];
                parent[t] = s;
                weight[s] = weight[t];
                weight[t] = f;
            }
        }
        return new int[][] {parent, weight};
    }

    // Reject missing, empty, non-square, or negative-capacity matrices.
    private static void validateCapacityMatrix(int[][] cap) {
        if (cap == null || cap.length == 0) {
            throw new IllegalArgumentException("Capacity matrix must not be null or empty");
        }
        final int n = cap.length;
        for (int i = 0; i < n; i++) {
            if (cap[i] == null || cap[i].length != n) {
                throw new IllegalArgumentException("Capacity matrix must be square");
            }
            for (int j = 0; j < n; j++) {
                if (cap[i][j] < 0) {
                    throw new IllegalArgumentException("Capacities must be non-negative");
                }
            }
        }
    }

    // Stores the maximum flow and the vertices on the source side
    // of the minimum cut found in the final residual graph.
    private static final class MaxFlowResult {
        final int flow;
        final boolean[] reachable;
        MaxFlowResult(int flow, boolean[] reachable) {
            this.flow = flow;
            this.reachable = reachable;
        }
    }

    private static MaxFlowResult edmondsKarpWithMinCut(int[][] capacity, int source, int sink) {
        final int n = capacity.length;
        int[][] residual = new int[n][n];

        // Use a separate residual matrix to preserve the input capacities.
        for (int i = 0; i < n; i++) {
            residual[i] = Arrays.copyOf(capacity[i], n);
        }

        int[] parent = new int[n];
        int maxFlow = 0;

        // Repeatedly augment along a shortest path in number of edges.
        while (bfs(residual, source, sink, parent)) {
            // The smallest residual capacity limits the flow on this path.
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residual[u][v]);
            }

            // Consume forward capacity and add reverse capacity so later
            // augmenting paths can cancel previously assigned flow.
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }

        // With no augmenting path remaining, residual reachability identifies
        // the source side of a minimum cut.
        boolean[] reachable = new boolean[n];
        markReachable(residual, source, reachable);
        return new MaxFlowResult(maxFlow, reachable);
    }

    // Records predecessors along a residual path and reports whether the sink
    // can be reached. A parent value of -1 also marks an unvisited vertex.
    private static boolean bfs(int[][] residual, int source, int sink, int[] parent) {
        Arrays.fill(parent, -1);
        parent[source] = source;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < residual.length; v++) {
                if (residual[u][v] > 0 && parent[v] == -1) {
                    parent[v] = u;
                    if (v == sink) {
                        return true;
                    }
                    q.add(v);
                }
            }
        }
        return false;
    }

    // Explore all vertices reachable from the source through edges
    // that still have positive residual capacity.
    private static void markReachable(int[][] residual, int source, boolean[] vis) {
        Arrays.fill(vis, false);
        Queue<Integer> q = new ArrayDeque<>();
        vis[source] = true;
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < residual.length; v++) {
                if (!vis[v] && residual[u][v] > 0) {
                    vis[v] = true;
                    q.add(v);
                }
            }
        }
    }
}