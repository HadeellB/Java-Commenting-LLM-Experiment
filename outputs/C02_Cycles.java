package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Scanner;

// Finds directed cycles using depth-first search and an adjacency matrix.
class Cycle {

    private final int nodes;
    private int[][] adjacencyMatrix;

    // Tracks nodes on the current search path.
    private boolean[] visited;

    // Stores each cycle without repeating its starting node at the end.
    ArrayList<ArrayList<Integer>> cycles = new ArrayList<ArrayList<Integer>>();

    Cycle() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the no. of nodes: ");
        nodes = in.nextInt();
        System.out.print("Enter the no. of Edges: ");
        final int edges = in.nextInt();

        adjacencyMatrix = new int[nodes][nodes];
        visited = new boolean[nodes];

        for (int i = 0; i < nodes; i++) {
            visited[i] = false;
        }

        System.out.println("Enter the details of each edges <Start Node> <End Node>");

        // Node indices must be between 0 and nodes - 1.
        for (int i = 0; i < edges; i++) {
            int start;
            int end;
            start = in.nextInt();
            end = in.nextInt();

            // Records a directed edge from start to end.
            adjacencyMatrix[start][end] = 1;
        }
        in.close();
    }

    public void start() {
        for (int i = 0; i < nodes; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            dfs(i, i, temp);

            // Removes all edges involving this starting node so later searches
            // do not rediscover its cycles from a different starting node.
            for (int j = 0; j < nodes; j++) {
                adjacencyMatrix[i][j] = 0;
                adjacencyMatrix[j][i] = 0;
            }
        }
    }

    // Explores paths from curr, looking for an edge back to start.
    private void dfs(Integer start, Integer curr, ArrayList<Integer> temp) {
        temp.add(curr);
        visited[curr] = true;
        for (int i = 0; i < nodes; i++) {
            if (adjacencyMatrix[curr][i] == 1) {
                if (i == start) {
                    // Copies the path because backtracking will modify temp.
                    cycles.add(new ArrayList<Integer>(temp));
                } else {
                    // Prevents revisiting a node already on the current path.
                    if (!visited[i]) {
                        dfs(start, i, temp);
                    }
                }
            }
        }

        // Backtracks so other paths can use this node.
        if (temp.size() > 0) {
            temp.remove(temp.size() - 1);
        }
        visited[curr] = false;
    }

    public void printAll() {
        for (int i = 0; i < cycles.size(); i++) {
            for (int j = 0; j < cycles.get(i).size(); j++) {
                System.out.print(cycles.get(i).get(j) + " -> ");
            }

            // Repeats the starting node to show the cycle's closing edge.
            System.out.println(cycles.get(i).get(0));
            System.out.println();
        }
    }
}

public final class Cycles {
    private Cycles() {
    }

    public static void main(String[] args) {
        Cycle c = new Cycle();
        c.start();
        c.printAll();
    }
}