package leetcode.dp;

public class LongestPalindromicSubstring_5 {
    public String longestPalindrome(String s) {
        // return expandAroundCenter(s);
        return dynamicProgramming(s);
    }
    
    private String dynamicProgramming(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0, end = 0;
        boolean[] dp1 = new boolean[s.length()];
        
        for (int i = s.length() - 1; i >= 0 ; i--) {
            boolean[] dp2 = new boolean[s.length()];
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    dp2[j] = true;
                } else if (i + 1 == j) {
                    dp2[j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp2[j] = dp1[j - 1] && s.charAt(i) == s.charAt(j);
                }
                
                if (dp2[j] && j - i + 1 > end - start) {
                    start = i;
                    end = j + 1;
                }
            }
            dp1 = dp2;
        }
        return s.substring(start, end);
    }
    
    private String expandAroundCenter(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = helper(s, i, i);
            int len2 = helper(s, i, i + 1);
            
            int len = Math.max(len1, len2);
            if (end - start < len) {
                start = i - ((len - 1) >> 1);
                end = start + len;
            }
        }
        return s.substring(start, end);
    }
    
    private int helper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}
