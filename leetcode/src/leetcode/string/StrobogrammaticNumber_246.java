package leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StrobogrammaticNumber_246 {
    private static final Set<Character> SINGLES = new HashSet<>();
    private static final Map<Character, Character> DOUBLES = new HashMap<>();
    static {
        SINGLES.addAll(Arrays.asList('1', '8', '0'));
        DOUBLES.put('9', '6');
        DOUBLES.put('6', '9');
    }
    public boolean isStrobogrammatic(String num) {
        if (num.length() == 0) {
            return true;
        }
        for (int i = 0, j = num.length() - 1; i <= j; i++, j--) {
            char ci = num.charAt(i);
            char cj = num.charAt(j);
            
            if (SINGLES.contains(ci)) {
                if (ci != cj) {
                    return false;
                }
            } else if (DOUBLES.containsKey(ci)) {
                if (DOUBLES.get(ci) != cj) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
