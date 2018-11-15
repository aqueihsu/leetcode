package leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GroupAnagrams_49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counters = new int[26];
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                counters[c - 'a']++;
            }
            
            StringBuilder builder = new StringBuilder();
            for (int counter : counters) {
                builder.append(counter);
                builder.append('.');
            }
            
            int hash = Objects.hash(builder.toString());
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<>());
            }
            map.get(hash).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
