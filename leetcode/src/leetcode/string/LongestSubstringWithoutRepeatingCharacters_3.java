package leetcode.string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters_3 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charToIdxMap = new HashMap<>();
        
        int maxLen = 0;
        for (int i = 0, minIdx = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charToIdxMap.containsKey(c)) {
                minIdx = Math.max(minIdx, charToIdxMap.get(c) + 1);
            }
            charToIdxMap.put(c, i);
            maxLen = Math.max(maxLen, i - minIdx + 1);
        }
        return maxLen;
    }
    
    public int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> charToIdxMap = new HashMap<>();
        
        int len = 0, maxLen = len;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charToIdxMap.containsKey(c)) {
                int minIdx = charToIdxMap.get(c);
                truncateMap(charToIdxMap, minIdx);
                charToIdxMap.put(c, i);
                len = i - minIdx - 1;
            } else {
                charToIdxMap.put(c, i);
            }
            len++;
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
    
    private void truncateMap(Map<Character, Integer> charToIdxMap, int minIdx) {
        Iterator<Map.Entry<Character, Integer>> itor = charToIdxMap.entrySet().iterator();
        while (itor.hasNext()) {
            Map.Entry<Character, Integer> entry = itor.next();
            int idx = entry.getValue();
            if (idx < minIdx) {
                itor.remove();
            }
        }
    }
}
