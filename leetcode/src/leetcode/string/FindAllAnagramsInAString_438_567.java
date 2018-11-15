package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString_438_567 {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char pc = p.charAt(i);
            map.put(pc, map.getOrDefault(pc, 0) + 1);
        }
        int counter = map.size();
        
        List<Integer> results = new ArrayList<>();
        int begin = 0, end = 0;
        
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
            while (counter == 0) { // All characters hit
                if (end - begin == p.length()) {
                    results.add(begin);
                }
                
                // Taking one char out of the current window
                char bc = s.charAt(begin);
                if (map.containsKey(bc)) {
                    int remaining = map.get(bc);
                    map.put(bc, ++remaining);
                    if (remaining > 0) {
                        // Now we are one character short of the target string
                        // This will exist the loop and move the sliding window forward
                        counter++;
                    }
                }
                begin++;
            }
        }
        return results;
    }
}
