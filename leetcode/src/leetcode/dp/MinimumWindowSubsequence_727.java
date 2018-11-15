package leetcode.dp;

public class MinimumWindowSubsequence_727 {
    public String minWindow(String S, String T) {
        if (S == null || S.isEmpty()) {
            return S;
        }
        int[][] dp = new int[S.length() + 1][T.length() + 1];
        for (int i = 0; i <= S.length(); i++) {
            dp[i][0] = i;
        }
        
        for (int i = 0; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                if (i < j) {
                    dp[i][j] = -1;
                } else if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        int minLen = Integer.MAX_VALUE;
        int start = -1;
        for (int i = T.length(); i <= S.length(); i++) {
            if (dp[i][T.length()] != -1 && minLen > i - dp[i][T.length()]) {
                minLen = i - dp[i][T.length()];
                start = dp[i][T.length()];
            }
        }
        return start == -1 ? "" : S.substring(start, start + minLen);
    }
}
