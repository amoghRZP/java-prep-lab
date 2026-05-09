package graph;
import java.util.*;

public class GraphDFS {
    private ArrayList<ArrayList<Integer>> adjList;
    private boolean[] visited;

    // Graph initialization
    public GraphDFS(int vertices) {
        adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[vertices];
    }

    // Adding edges
    public void addEdge(int v, int w) {
        adjList.get(v).add(w);
    }

    // DFS using recursion
    public void dfsRecursive(int v) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                dfsRecursive(neighbor);
            }
        }
    }

    // DFS using stack
    public void dfsStack(int startVertex) {
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                visited[vertex] = true;
                System.out.print(vertex + " ");
                for (int neighbor : adjList.get(vertex)) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphDFS g = new GraphDFS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("DFS starting from vertex 2 (recursive):");
        g.dfsRecursive(2);

        System.out.println("\nDFS starting from vertex 2 (using stack):");
        g.dfsStack(2);
    }
}
