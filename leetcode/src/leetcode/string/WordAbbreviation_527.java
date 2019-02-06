package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordAbbreviation_527 {
    public List<String> wordsAbbreviation(List<String> dict) {
        // Group strings by length, first char, and last char
        Map<String, List<Integer>> abbrevGroups = new HashMap<>();
        for (int i = 0; i < dict.size(); i++) {
            String string = dict.get(i);
            int len = string.length();
            String abbrev = len - 2 <= 1 ? string :
                String.format("%c%d%c", string.charAt(0), len - 2, string.charAt(len - 1));
            
            if (!abbrevGroups.containsKey(abbrev)) {
                abbrevGroups.put(abbrev, new ArrayList<>());
            }
            abbrevGroups.get(abbrev).add(i);
        }
        
        // For each group, if there's multiple elements, build a trie to find the longest prefix
        String[] results = new String[dict.size()];
        for (Map.Entry<String, List<Integer>> group : abbrevGroups.entrySet()) {
            List<Integer> indices = group.getValue();
            
            if (indices.size() == 1) {
                // Unique abbreviation
                int index = indices.get(0);
                String abbrev = group.getKey();
                
                results[index] = abbrev;
            } else {
                // Not unique, need to find the longest prefix
                Trie trie = new Trie();
                for (int index : indices) {
                    trie.addString(dict.get(index));
                }
                
                for (int index : indices) {
                    String string = dict.get(index);
                    int prefixLen = trie.uniquePrefixLength(string);
                    int len = string.length();
                    String abbrev = len - 1 - prefixLen <= 1 ? string :
                        String.format("%s%d%c", string.substring(0, prefixLen), len - 1 - prefixLen, string.charAt(len - 1));
                    
                    results[index] = abbrev;
                }
            }
        }
        return Arrays.asList(results);
    }
    
    private class Node {
        Map<Character, Node> childMap = new HashMap<>();
        int count;
    }
    
    private class Trie {
        Node root = new Node();
        
        void addString(String string) {
            Node node = root;
            for (char c : string.toCharArray()) {
                if (!node.childMap.containsKey(c)) {
                    node.childMap.put(c, new Node());
                }
                node = node.childMap.get(c);
                node.count++;
            }
        }
        
        int uniquePrefixLength(String string) {
            Node node = root;
            int len = 0;
            for (char c : string.toCharArray()) {
                node = node.childMap.get(c);
                len++;
                if (node.count == 1) {
                    return len;
                }
            }
            return len;
        }
    }
}
