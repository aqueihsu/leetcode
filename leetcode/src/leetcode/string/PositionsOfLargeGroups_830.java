package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositionsOfLargeGroups_830 {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> results = new ArrayList<>();
        
        int i = 0, j = 0;
        while (j <= S.length()) {
            if (j == S.length() || S.charAt(j) != S.charAt(i)) {
                if (j - i >= 3) {
                    results.add(Arrays.asList(i, j - 1));
                }
                i = j;
            }
            j++;
        }
        return results;
    }
}
