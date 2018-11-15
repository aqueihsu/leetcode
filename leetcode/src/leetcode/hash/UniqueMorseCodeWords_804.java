package leetcode.hash;

import java.util.HashSet;
import java.util.Set;

public class UniqueMorseCodeWords_804 {
    private static String[] MORSE_CODE = new String[] {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    public int uniqueMorseRepresentations(String[] words) {
        Set<Integer> results = new HashSet<>();
        for (String word : words) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                builder.append(MORSE_CODE[c - 'a']);
            }
            results.add(builder.toString().hashCode());
        }
        return results.size();
    }
}
