package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove stones uses graph's number of disconnected components algorithm
 * maximum number stones which can be removed if stones can be removed in same row or column
 *   0 1 2 3 4
 * 0 s s s s
 * 1 s   s
 * 2   s   s
 * 3         s
 * input = [[0,0], [0,1], [0,2], [0,3], [1,0], [1,2], [2,1], [2,3], [3,4]]
 * answer = 7
 * removed stones will be = total stones - number of disconnected components
 */
public class RemoveStones {
    public static class Coordinates {
        int x;
        int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static int findNumberOfStonesToBeRemoved(int[][] stones) {
        List<Coordinates> coordinatesList = new ArrayList<>();
        for (int i=0; i<stones.length; i++) {
            int x = stones[i][0];
            int y = stones[i][1];
            coordinatesList.add(new Coordinates(x, y));
        }

        /*
        a -> b c
        b -> a
        c -> a
         */
        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i < coordinatesList.size(); i++) {
            adj.add(new ArrayList<>());
            for (int j=0; j < coordinatesList.size(); j++) {
                if(i!=j) {
                    if (coordinatesList.get(i).x == coordinatesList.get(j).x || coordinatesList.get(i).y == coordinatesList.get(j).y) {
                        adj.get(i).add(j);
                    }
                }
            }
        }

        int[] vis = new int[coordinatesList.size()];
        int islands = 0;
        for(int i=0; i < adj.size(); i++) {
            if(vis[i] == 0) {
                System.out.println("start dfs of: "+ i);
                islands++;
                dfs(i, adj, vis);
            }
        }

        return coordinatesList.size() - islands;
    }

    public static void dfs(int i, List<List<Integer>> adj, int[] vis) {
        if (vis[i] == 1) {
            return;
        }
        vis[i] = 1;
        for(int node : adj.get(i)) {
            dfs(node, adj, vis);
        }
    }

    public static void main(String[] args) {
        int[][] input1 = {{0,0}, {0,1}, {0,2}, {0,3}, {1,0}, {1,2}, {2,1}, {2,3}, {3,4}};
        System.out.println("Number of stones to be removed: "+ findNumberOfStonesToBeRemoved(input1));
    }
}
