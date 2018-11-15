package leetcode.dp;

import java.util.Arrays;

public class UniquePaths_62 {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        for (int j = 1; j < n; j++) {
            for (int i = 1; i < m; i ++) {
                dp[i] += dp[i - 1];
            }
        }
        return dp[m - 1];
    }
}
