package leetcode.review;

public class ShortestPalindrome_214 {
    public String shortestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        int idx = 0;
        int maxLen = -1;
        for (int i = 1; i < s.length(); i++) {
            int len = Math.max(helper(s, i, i), helper(s, i - 1, i));
            if (maxLen < len) {
                idx = i;
                maxLen = len;
            }
        }
        StringBuilder builder;
        if (maxLen == -1) {
            builder = new StringBuilder(s.substring(1, s.length()));
        } else {
            int end = idx - (maxLen >> 1) + maxLen;
            builder = new StringBuilder(s.substring(end, s.length()));
        }
        builder.reverse().append(s);
        return builder.toString();
    }
    
    private int helper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return l == 0 ? r - l + 1 : -1;
    }
}
