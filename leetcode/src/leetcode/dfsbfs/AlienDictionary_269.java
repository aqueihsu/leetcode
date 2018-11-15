package leetcode.dfsbfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AlienDictionary_269 {
    public String alienOrder(String[] words) {
        // Building the adj map
        Map<Integer, Set<Integer>> adj = new HashMap<>(26);
        
        String prevWord = null;
        for (int i = 0; i < words.length; i++) {
            String currWord = words[i];
            boolean matches = true;
            for (int j = 0; j < currWord.length(); j++) {
                int c = currWord.charAt(j) - 'a';
                if (!adj.containsKey(c)) {
                    adj.put(c, new HashSet<>());
                }
                
                if (matches && prevWord != null && j < prevWord.length()) {
                    int p = prevWord.charAt(j) - 'a';
                    if (!(matches = p == c)) {
                        adj.get(p).add(c);
                    }
                }
            }
            prevWord = currWord;
        }
        
        boolean[] discovered = new boolean[26], completed = new boolean[26];
        StringBuilder builder = new StringBuilder();
        for (int i : adj.keySet()) {
            if (!topologySort(adj, i, discovered, completed, builder)) {
                return "";
            }
        }
        return builder.reverse().toString();
    }
    
    private boolean topologySort(Map<Integer, Set<Integer>> adj, int i, boolean[] discovered, boolean[] completed, StringBuilder builder) {
        if (completed[i]) {
            return true;
        }
        discovered[i] = true;
        if (adj.containsKey(i)) {
            Set<Integer> neighbors = adj.get(i);
            for (int neighbor : neighbors) {
                if (discovered[neighbor] && !completed[neighbor]
                        || !topologySort(adj, neighbor, discovered, completed, builder)) {
                    return false;
                }
            }
        }
        builder.append((char) (i + 'a'));
        return completed[i] = true;
    }
}
