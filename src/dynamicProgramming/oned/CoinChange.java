package dynamicProgramming.oned;

/*
https://leetcode.com/problems/coin-change

You are given an integer array coins representing coins of different denominations
and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount.
 If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.



Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0

 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        //recursiveCoinChange(coins, amount, dp);
        //return dp[amount] == 100000 ? -1: dp[amount];

        return bottomUp(coins, amount);
    }

    public int recursiveCoinChange(int[] coins, int amount, int[] dp) {
        int result = 100000;
        if (amount < 0) {
            return result;
        }

        if (amount == 0) {
            return dp[amount] = 0;
        }

        if (dp[amount] != 0) {
            return dp[amount];
        }

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                result = Math.min(result, 1 + recursiveCoinChange(coins, amount - coins[i], dp));
            }
        }

        return dp[amount] = result;
    }

    public int bottomUp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int result = 100000;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    result = Math.min(result, 1 + dp[i - coins[j]]);
                }
            }
            dp[i] = result;
        }

        return dp[amount] >= 100000 ? -1 : dp[amount];
    }
}
