package graph;

import java.util.*;

public class GraphTopological {
    private final int V;   // No. of vertices
    private final ArrayList<ArrayList<Integer>> adj; // Adjacency list for the graph

    // Constructor
    GraphTopological(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i=0; i<v; ++i)
            adj.add(new ArrayList<>());
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj.get(v).add(w);
    }

    // A recursive function used by topologicalSort
    void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent to this vertex
        for (Integer adjNode : adj.get(v)) {
            if (!visited[adjNode])
                topologicalSortUtil(adjNode, visited, stack);
        }

        // Push current vertex to stack which stores result
        stack.push(v);
    }

    // The function to do Topological Sort. It uses recursive topologicalSortUtil()
    void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[V];

        // Mark all the vertices as not visited
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Call the recursive helper function to store Topological Sort
        // starting from all vertices one by one
        for (int i = 0; i < V; i++)
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);

        // Print contents of stack
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
    }


    // Function to perform topological sort using Kahn's algorithm
    void topologicalSortByIndegreeStartingFromRoot() {
        int indegree[] = new int[V];

        // Fill indegree array
        for (int i = 0; i < V; i++) {
            ArrayList<Integer> temp = adj.get(i);
            for (int node : temp) {
                indegree[node]++;
            }
        }

        // Queue for vertices with indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        Vector<Integer> topOrder = new Vector<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            topOrder.add(u);

            for (int node : adj.get(u)) {
                // Decrease indegree of adjacent vertices
                if (--indegree[node] == 0)
                    q.add(node);
            }
        }

        if (topOrder.size() != V) {
            System.out.println("There exists a cycle in the graph");
            return;
        }

        // Print topological order
        for (int i : topOrder) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        // Create a graph given in the above diagram
        GraphTopological g = new GraphTopological(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Following is a Topological sort of the given graph:");
        // Function Call
        g.topologicalSort();
        System.out.println("\nFollowing is a Topological sort of the given graph using Kahn's Algo:");
        g.topologicalSortByIndegreeStartingFromRoot();
    }
}

