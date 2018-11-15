package leetcode.diffnums;

public class FindTheDifference_389 {
    // Using "set" seems to be the fastest but takes extra space
    public char findTheDifference(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        
        char result = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); i++) {
            result ^= s.charAt(i);
            result ^= t.charAt(i);
        }
        return result;
    }
}
