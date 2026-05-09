package dynamicProgramming.oned;

/*
https://leetcode.com/problems/climbing-stairs
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 */
public class ClimbingStairs {

    public int bottomUpClimb(int n) {
        int[] dp = new int[n + 10];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int topDownClimb(int n) {
        return recursiveClimb(n);
    }

    public int recursiveClimb(int i) {
        if (i == 0) {
            return 1;
        }

        if (i < 0) {
            return 0;
        }

        return recursiveClimb(i - 1) + recursiveClimb(i - 2);
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.bottomUpClimb(5));
        System.out.println(climbingStairs.topDownClimb(5));
    }

}
