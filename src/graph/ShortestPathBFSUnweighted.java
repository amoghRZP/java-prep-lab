package graph;

import java.util.*;

/*
BFS is particularly effective for finding the shortest path in unweighted graphs because it explores nodes in layers.
Each layer represents nodes that are the same number of edges away from the source node.

Example of BFS Failing with Weighted Edges
Consider a simple graph with three nodes arranged in a triangle:

Node A is connected to Node B with a weight of 1.
Node A is connected to Node C with a weight of 10.
Node B is connected to Node C with a weight of 1.
If you use BFS starting from Node A to find the shortest path to Node C,
BFS will directly reach Node C from Node A because it only counts the number of edges.
It will conclude that the shortest path from Node A to Node C has a weight of 10 (directly from A to C),
ignoring a potentially shorter path via Node B (with a total weight of 2, from A to B to C).

Use Dijkstra or bellman ford for weighted graphs
 */
public class ShortestPathBFSUnweighted {
    private ArrayList<ArrayList<Integer>> adj;
    private int[] distance; // Distance array to store the shortest path length

    public ShortestPathBFSUnweighted(int vertices) {
        adj = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
        distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v); // Assuming it's an undirected graph
    }

    public void bfs(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        distance[startVertex] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : adj.get(current)) {
                if (distance[neighbor] == Integer.MAX_VALUE) { // Neighbor not visited
                    queue.add(neighbor);
                    distance[neighbor] = distance[current] + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        ShortestPathBFSUnweighted graph = new ShortestPathBFSUnweighted(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        graph.bfs(0); // Start BFS from vertex 0
        System.out.println(Arrays.toString(graph.distance)); // Print distances from source
    }
}

