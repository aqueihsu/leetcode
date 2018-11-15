package leetcode.string;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] counts = new int[26];
        int maxIdx = 0;
        int maxLen = -1;
        for (int i = 0, j = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'A';
            maxIdx = counts[maxIdx] < ++counts[c] ? c : maxIdx;
            
            while (i - j - counts[maxIdx] + 1 > k) {
                int prevC = s.charAt(j++) - 'A';
                counts[prevC]--;
                if (prevC == maxIdx) {
                    for (int p = 0; p < 26; p++) {
                        maxIdx = counts[maxIdx] < counts[p] ? p : maxIdx;
                    }
                }
            }
            maxLen = Math.max(maxLen, i - j + 1);
        }
        return maxLen;
    }
}
