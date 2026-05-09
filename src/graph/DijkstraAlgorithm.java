package graph;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Here's how Dijkstra's algorithm works:

Starting Point: You start from your house.
Distances List: You have a list that tells you how far each friend's house is from yours, starting with all of them being really, really far (except your house, which is at a distance of zero because you're already there!).
Check Neighbors: You first check all the houses (friends’ houses) you can go to directly from your house and update how long it takes to get to them (update their distance if new distance is less than earlier caluclated distance).
Choose the Nearest: Then you pick the closest friend’s house that you haven’t visited yet and repeat the checking from there.
Repeat: You keep doing this until you've found the shortest path to all your friends' houses.
 */
public class DijkstraAlgorithm {

    static class Node {
        int vertex, cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    public static int[] dijkstra(int[][] graph, int startVertex) {
        int n = graph.length;
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];

        // Set initial distances to infinity
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        /*PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.cost < o2.cost) {
                return -1;
            } else if (o1.cost > o2.cost) {
                return 1;
            } else {
                return 0;
            }
        });*/
        //PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        priorityQueue.add(new Node(startVertex, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentVertex = currentNode.vertex;

            if (visited[currentVertex]) continue;

            visited[currentVertex] = true;

            // Explore the neighbors
            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (graph[currentVertex][neighbor] != 0 && !visited[neighbor]) {
                    int newDist = distances[currentVertex] + graph[currentVertex][neighbor];
                    if (newDist < distances[neighbor]) {
                        distances[neighbor] = newDist;
                        priorityQueue.add(new Node(neighbor, newDist));
                    }
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 6, 0, 1, 0},
                {6, 0, 5, 2, 2},
                {0, 5, 0, 0, 5},
                {1, 2, 0, 0, 1},
                {0, 2, 5, 1, 0}
        };
        int startVertex = 0;
        int[] distances = dijkstra(graph, startVertex);

        System.out.println("The shortest paths from house " + startVertex + " to all other houses are:");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("To house " + i + " is " + distances[i]);
        }
    }
}
