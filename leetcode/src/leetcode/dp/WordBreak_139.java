package leetcode.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak_139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] validSegment = new boolean[s.length() + 1];
        validSegment[0] = true;
        
        Set<String> wordSet = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (validSegment[j] && wordSet.contains(s.substring(j, i))) {
                    validSegment[i] = true;
                    break;
                }
            }
        }
        
        return validSegment[s.length()];
    }
}
