package dynamicProgramming.oned;

import java.lang.Math;

/*
Given an integer array nums, return the length of the longest strictly increasing subsequence

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];

        // recursive LIS has to be called starting from each index
        // as calling from previous index may not necessarily cover all subsequent indexes in its recursion chain
        for (int i = 0; i < nums.length; i++) {
            recursiveLIS(nums, i, dp);
        }

        int max = dp[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(dp[i], max);
        }

        return max;
    }

    public int recursiveLIS(int[] nums, int i, int[] dp) {
        if (dp[i] != 0) {
            return dp[i];
        }
        int result = 1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] < nums[j]) {
                result = Math.max(result, 1 + recursiveLIS(nums, j, dp));
            }
        }

        return dp[i] = result;
    }

    public int bottomUp(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int answer = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] == 0) {
                dp[i] = 1;
            }
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }


}
