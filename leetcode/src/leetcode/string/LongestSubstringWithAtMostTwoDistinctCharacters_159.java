package leetcode.string;

public class LongestSubstringWithAtMostTwoDistinctCharacters_159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int idx1 = 0, idx2 = idx1;
        while (idx2 < s.length() && s.charAt(idx2) == s.charAt(idx1)) {
            idx2++;
        }

        if (idx2 == s.length()) {
            return s.length();
        }
        
        int len = idx2 + 1, maxLen = len;
        for (int i = idx2 + 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                if (s.charAt(idx1) != s.charAt(i)) {
                    len = i - idx2;
                }
                idx1 = idx2;
                idx2 = i;
            }
            len++;
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}
