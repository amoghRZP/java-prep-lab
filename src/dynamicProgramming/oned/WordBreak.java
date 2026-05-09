package dynamicProgramming.oned;

import java.util.*;

/*
https://leetcode.com/problems/word-break

Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.


 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Integer> dict = new HashMap<>();
        for (String word : wordDict) {
            dict.put(word, 1);
        }
        int[] dp = new int[s.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i] = -1;
        }

        return findSegmentedMemoized(0, s, dict, dp) == 0 ? false : true;
    }

    /*
    Pure recursion
     */
    public boolean findSegmented(int i, String s, Map<String, Integer> dict) {
        if (i >= s.length()) {
            return true;
        }


        for (int l = 1; l <= s.length(); l++) {
            if (i + l <= s.length() && dict.containsKey(s.substring(i, i + l)) && findSegmented(i + l, s, dict)) {
                return true;
            }
        }
        return false;
    }

    /*
    Memoized/cached recursion
     */
    public int findSegmentedMemoized(int i, String s, Map<String, Integer> dict, int[] dp) {
        // This mean we have reached one index after last char of string,
        // that means all substring till last char of string are in dict and that is why index reached here
        // so set it to true
        if (i >= s.length()) {
            return dp[i] = 1;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        // generate all length string starting from index i and check if they are in dict
        // if they are in dict call function recursively for rest of string
        for (int l = 1; l <= s.length(); l++) {
            if (i + l <= s.length() && dict.containsKey(s.substring(i, i + l))) {
                if (findSegmentedMemoized(i + l, s, dict, dp) == 1)
                    return dp[i] = 1;
            }
        }
        return dp[i] = 0;
    }

}
