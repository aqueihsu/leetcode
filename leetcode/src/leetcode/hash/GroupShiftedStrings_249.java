package leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedStrings_249 {
    public List<List<String>> groupStrings(String[] strings) {
        Map<Integer, List<String>> map = new HashMap<>();
        
        for (String string : strings) {
            int[] diffs = new int[string.length() - 1];
            for (int i = 0; i < string.length() - 1; i++) {
                diffs[i] = string.charAt(i) - string.charAt(i + 1);
                if (diffs[i] < 0) {
                    diffs[i] += 26;
                }
            }
            StringBuilder builder = new StringBuilder();
            for (int diff : diffs) {
                builder.append(diff);
                builder.append('#');
            }
            int hash = builder.toString().hashCode();
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<>());
            }
            map.get(hash).add(string);
        }
        return new ArrayList<>(map.values());
    }
}
