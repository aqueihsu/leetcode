package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestWordInDictionaryThroughDeleting_524 {
    public String findLongestWord(String s, List<String> dict) {
        Map<Character, List<Integer>> charToPosMap = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            charToPosMap.put(c, new ArrayList<>());
        }
        for (int i = 0; i < s.length(); i++) {
            charToPosMap.get(s.charAt(i)).add(i);
        }
        
        int iMaxWord = -1;
        for (int i = 0; i < dict.size(); i++) {
            boolean hasMatch = true;
            int prevWord = -1;
            
            String word = dict.get(i);
            for (int j = 0; j < word.length(); j++) {
                List<Integer> positions = charToPosMap.get(word.charAt(j));
                int iPos = Collections.binarySearch(positions, prevWord + 1);
                if (iPos < 0) {
                    iPos = -iPos - 1;
                }
                if (iPos >= positions.size()) {
                    hasMatch = false;
                    break;
                }
                prevWord = positions.get(iPos);
            }
            if (hasMatch) {
                if (iMaxWord == - 1 || dict.get(iMaxWord).length() < word.length()
                        || dict.get(iMaxWord).length() == word.length() && dict.get(iMaxWord).compareTo(word) > 0) {
                    iMaxWord = i;
                }
            }
        }
        return iMaxWord == -1 ? "" : dict.get(iMaxWord);
    }
}
