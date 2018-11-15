package leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters_340 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0 || s == null || s.isEmpty()) {
            return 0;
        }
        int nDistincts = 0;
        int[] charCounters = new int[256];
        int maxLen = -1;
        for (int i = 0, j = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (charCounters[c]++ == 0) {
                nDistincts++;
                while (nDistincts > k) {
                    int prevC = s.charAt(j++);
                    if (--charCounters[prevC] == 0) {
                        nDistincts--;
                    }
                }
            }
            maxLen = Math.max(maxLen, i - j + 1);
        }
        return maxLen;
    }
    
    public int lengthOfLongestSubstringKDistinct1(String s, int k) {
        if (k == 0 || s == null || s.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> charToEndIdxMap = new HashMap<>();
        
        int numDistinctChars = 1;
        int maxLen = 1;
        for (int i = 1, j = 0; i < s.length(); i++) {
            if (s.charAt(i - 1) != s.charAt(i)) {
                char prevC = s.charAt(i - 1);
                charToEndIdxMap.put(prevC, i);
                
                char currC = s.charAt(i);
                int prevEndIdxC = charToEndIdxMap.getOrDefault(currC, -1);
                
                if (prevEndIdxC <= j) {
                    // c is not in the current substring
                    if (numDistinctChars == k) {
                        // reached k, need to drop the head character
                        j = truncateMinChar(j, charToEndIdxMap);
                    } else {
                        // increase the count
                        numDistinctChars++;
                    }
                }
                
            }
            maxLen = Math.max(maxLen, i - j + 1);
        }
        return maxLen;
    }
    
    private int truncateMinChar(int lowerBound, Map<Character, Integer> charToEndIdxMap) {
        int minIdx = Integer.MAX_VALUE;
        for (int value : charToEndIdxMap.values()) {
            if (value > lowerBound) {
                if (minIdx > value) {
                    minIdx = value;
                }
            }
        }
        return minIdx;
    }
}
