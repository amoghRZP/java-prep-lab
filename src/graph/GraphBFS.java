package graph;
import java.util.*;

public class GraphBFS {
    private ArrayList<ArrayList<Integer>> adj; // Graph represented as an adjacency list
    private boolean[] visited; // Array to track visited nodes

    // Graph constructor
    public GraphBFS(int vertices) {
        adj = new ArrayList<>(vertices);
        visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Method to add edges to the graph
    public void addEdge(int v, int w) {
        adj.get(v).add(w); // Add w to v's list.
    }

    // BFS algorithm
    public void bfs(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll(); // Get the next vertex to process
            System.out.print(vertex + " "); // Print the vertex

            // Process all adjacent vertices
            for (int neighbor : adj.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphBFS g = new GraphBFS(4); // Create a graph with 4 vertices

        // Add edges to the graph
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("BFS starting from vertex 2:");
        g.bfs(2); // Perform BFS from vertex 2
    }
}

