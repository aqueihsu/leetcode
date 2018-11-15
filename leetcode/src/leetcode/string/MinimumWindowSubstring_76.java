package leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring_76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char tc = t.charAt(i);
            map.put(tc, map.getOrDefault(tc, 0) + 1);
        }
        
        int counter = map.size();
        int begin = 0, end = 0;
        
        int minLen = Integer.MAX_VALUE;
        int minBegin = -1;
        
        while (end < s.length()) {
            char ec = s.charAt(end);
            if (map.containsKey(ec)) {
                int remaining = map.get(ec);
                map.put(ec, --remaining);
                if (remaining == 0) {
                    counter--;
                }
            }
            end++;
            
            while (counter == 0) {
                int len = end - begin;
                if (minLen > len) {
                    minLen = len;
                    minBegin = begin;
                }
                
                char bc = s.charAt(begin);
                if (map.containsKey(bc)) {
                    int remaining = map.get(bc);
                    map.put(bc, ++remaining);
                    if (remaining > 0) {
                        counter++;
                    }
                }
                begin++;
            }
        }
        return minBegin == -1 ? "" : s.substring(minBegin, minBegin + minLen);
    }
}
