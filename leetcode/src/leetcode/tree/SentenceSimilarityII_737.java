package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

public class SentenceSimilarityII_737 {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, String> parents = new HashMap<>();
        for (String[] pair : pairs) {
            parents.put(pair[0], pair[0]);
            parents.put(pair[1], pair[1]);
        }
        for (String[] pair : pairs) {
            String parent = find(parents, pair[0]);
            parents.put(find(parents, pair[1]), parent);
        }
        
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }
            String parent1 = find(parents, words1[i]);
            String parent2 = find(parents, words2[i]);
            if (!parent1.equals(parent2)) {
                return false;
            }
        }
        return true;
    }
    
    private String find(Map<String, String> parents, String word) {
        String parent = parents.get(word);
        if (parent == null) {
            return word;
        }
        if (!parent.equals(word)) {
            parents.put(word, parent = find(parents, parent));
        }
        return parent;
    }
}
