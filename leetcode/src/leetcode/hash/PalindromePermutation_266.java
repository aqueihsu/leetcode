package leetcode.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PalindromePermutation_266 {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        return set.size() <= 1;
    }
    
    public boolean canPermutePalindrome1(String s) {
        Map<Character, Integer> counters = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            counters.put(c, counters.getOrDefault(c, 0) + 1);
        }
        int numberOddCount = 0;
        for (int count : counters.values()) {
            if (count % 2 == 1 && ++numberOddCount > 1) {
                return false;
            }
        }
        return true;
    }
}
