package leetcode.dp;

public class PalindromicSubstrings_647 {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        boolean[] dp1 = new boolean[s.length()];
        
        for (int i = s.length() - 1; i >= 0; i--) {
            boolean[] dp2 = new boolean[s.length()];
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    dp2[j] = true;
                } else if (i + 1 == j) {
                    dp2[j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp2[j] = dp1[j - 1] && s.charAt(i) == s.charAt(j);
                }
                if (dp2[j]) {
                    count++;
                }
            }
            dp1 = dp2;
        }
        return count;
    }
}
