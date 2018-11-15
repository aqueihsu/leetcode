package leetcode.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SentenceSimilarity_734 {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, Set<String>> similarities = new HashMap<>();
        for (String[] pair : pairs) {
            if (!similarities.containsKey(pair[0])) {
                similarities.put(pair[0], new HashSet<>());
            }
            if (!similarities.containsKey(pair[1])) {
                similarities.put(pair[1], new HashSet<>());
            }
            similarities.get(pair[0]).add(pair[1]);
            similarities.get(pair[1]).add(pair[0]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (!words1[i].equals(words2[i]) && (!similarities.containsKey(words1[i])
                    || !similarities.get(words1[i]).contains(words2[i]))) {
                return false;
            }
        }
        return true;
    }
}
