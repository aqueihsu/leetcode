package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings_271 {
    
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs == null || strs.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String str : strs) {
            builder.append(str.length());
            builder.append('/');
            builder.append(str);
        }
        return builder.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> strings = new ArrayList<>();
        
        if (s == null || s.isEmpty()) {
            return strings;
        }
        int i = 0;
        while (i < s.length()) {
            int slash = s.indexOf('/', i);
            int len = Integer.valueOf(s.substring(i, slash));
            // Note: for substring, It's ok if beginIndex == length
            strings.add(s.substring(slash + 1, i = slash + len + 1));
        }
        return strings;
    }
}
