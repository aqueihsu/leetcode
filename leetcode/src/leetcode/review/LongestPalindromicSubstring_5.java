package leetcode.review;

public class LongestPalindromicSubstring_5 {
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        int idx = 0;
        int longest = 1;
        
        int n = s.length();
        for (int i = 1; i < n; i++) {
            int len = Math.max(helper(s, i, i), helper(s, i - 1, i));
            if (longest < len) {
                longest = len;
                idx = i;
            }
        }
        if (longest == Integer.MIN_VALUE) {
            return s.substring(0, 1);
        } else {
            int start = idx - (longest >> 1);
            return s.substring(start, start + longest);
        }
    }
    
    private int helper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
    
    public String longestPalindromeDP(String s) {
        if (s.length() == 0) {
            return "";
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        
        int start = 0, end = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (j == i) {
                    dp[i][j] = true;
                } else if (j == i + 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                
                if (dp[i][j] && j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }
    
    public String longestPalindromeRecurr(String s) {
        if (s.length() == 0) {
            return "";
        }
        int n = s.length();
        boolean[][] visited = new boolean[n][n];
        boolean[][] cache = new boolean[n][n];
        
        int start = 0, end = 0;
        int longestLen = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(visited, cache, i, j, s) && longestLen < j - i) {
                    start = i;
                    end = j;
                    longestLen = j - i;
                }
            }
        }
        return s.substring(start, end + 1);
    }
    
    private boolean isPalindrome(boolean[][] visited, boolean[][] cache, int i, int j, String s) {
        if (j == i) {
            return true;
        } else if (j == i + 1) {
            return s.charAt(i) == s.charAt(j);
        }
        if (visited[i][j]) {
            return cache[i][j];
        }
        visited[i][j] = true;
        return cache[i][j] = s.charAt(i) == s.charAt(j) && isPalindrome(visited, cache, i + 1, j - 1, s);
    }
}
