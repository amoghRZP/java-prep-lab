package dynamicProgramming.twod;

import java.lang.Math;
import java.util.*;

public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            Arrays.fill(dp[i], -1);
        }

        recursiveLCS(text1, text2, 0, 0, dp);

        return dp[0][0];

        /*
        //Note: Not required because its considering all combination of char and returning max to calling method
        int maxLength = 0;
        for(int i=0; i<text1.length(); i++) {
            for(int j=0; j<text2.length(); j++) {
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;*/
    }

    public int recursiveLCS(String s1, String s2, int i, int j, int[][] dp) {
        if (i >= s1.length() || j >= s2.length()) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int result = 0;
        if (s1.charAt(i) == s2.charAt(j)) {
            result = 1 + recursiveLCS(s1, s2, i + 1, j + 1, dp);
        }
        result = Math.max(result, recursiveLCS(s1, s2, i + 1, j, dp));
        result = Math.max(result, recursiveLCS(s1, s2, i, j + 1, dp));

        return dp[i][j] = result;
    }

}
