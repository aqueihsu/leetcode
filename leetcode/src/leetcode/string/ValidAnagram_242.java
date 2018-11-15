package leetcode.string;

public class ValidAnagram_242 {
    public boolean isAnagram(String s, String t) {
        int[] counters = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            counters[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            counters[t.charAt(i) - 'a']--;
        }
        for (int counter : counters) {
            if (counter != 0) {
                return false;
            }
        }
        return true;
    }
}
