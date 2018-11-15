package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsSubsequence_392 {
    public boolean isSubsequence(String s, String t) {
        return isSubsequence_hashmap(s, t);
        // return isSubsequence_loop(s, t);
    }
    
    // Scalable when there are many s
    private boolean isSubsequence_hashmap(String s, String t) {
        Map<Character, List<Integer>> charToPositionsMap = new HashMap<>();
        for (int ti = 0; ti < t.length(); ti++) {
            char tc = t.charAt(ti);
            if (!charToPositionsMap.containsKey(tc)) {
                charToPositionsMap.put(tc, new ArrayList<Integer>());
            }
            charToPositionsMap.get(tc).add(ti);
        }
        
        int prevPosition = -1;
        for (int si = 0; si < s.length(); si++) {
            char sc = s.charAt(si);
            if (!charToPositionsMap.containsKey(sc)) {
                return false;
            }
            
            List<Integer> positions = charToPositionsMap.get(sc);
            int pi = Collections.binarySearch(positions, prevPosition);
            if (pi < 0) {
                pi = -pi - 1;
            }
            if (pi == positions.size()) {
                return false;
            }
            prevPosition = positions.get(pi) + 1;
        }
        return true;
    }
    
    private boolean isSubsequence_loop(String s, String t) {
        int si = 0, ti = 0;
        while (si < s.length() && ti < t.length()) {
            if (s.charAt(si) == t.charAt(ti)) {
                si++;
            }
            ti++;
        }
        
        return si >= s.length();
    }
}
