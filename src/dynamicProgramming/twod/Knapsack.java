package dynamicProgramming.twod;

import java.lang.Math;

public class Knapsack {
    public static int findMaxKnapsackProfit(int capacity, int[] weights, int[] values) {
        int[][] dp = new int[values.length + 1][capacity + 1];

        for (int i = 0; i < values.length + 1; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j < capacity + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < values.length + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                if (j - weights[i - 1] >= 0) { // If current item can fit
                    dp[i][j] = Math.max(values[i - 1] + dp[i - 1][j - weights[i - 1]], // Include item
                            dp[i - 1][j]); // Exclude item
                } else {
                    dp[i][j] = dp[i - 1][j]; // Exclude item
                }
            }
        }

        return dp[values.length][capacity];
    }
}
