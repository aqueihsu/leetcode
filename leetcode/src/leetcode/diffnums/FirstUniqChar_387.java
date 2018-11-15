package leetcode.diffnums;

import java.util.Arrays;

public class FirstUniqChar_387 {
    public int firstUniqChar(String s) {
        if (s == null) {
            return -1;
        }
        
        final int repeated = s.length();
        final int notPresent = repeated + 1;
        
        int[] uniqueCharPos = new int[26];
        Arrays.fill(uniqueCharPos, notPresent);
        
        for (int pos = 0; pos < s.length(); pos++) {
            int index = s.charAt(pos) - 'a';
            if (uniqueCharPos[index] == notPresent) {
                uniqueCharPos[index] = pos;
            } else {
                uniqueCharPos[index] = repeated;
            }
        }
        
        int minPos = repeated;
        for (int pos : uniqueCharPos) {
            if (minPos > pos) {
                minPos = pos;
            }
        }
        return minPos == repeated ? -1 : minPos;
    }
}
