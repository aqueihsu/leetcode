package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class StrobogrammaticNumberII_247 {
    private static final char[] SINGLES = new char[] {'1', '8', '0'};
    private static final char[][] PAIRS = new char[][] {{'1', '1'}, {'8', '8'}, {'0', '0'}, {'9', '6'}, {'6', '9'}};
    public List<String> findStrobogrammatic(int n) {
        List<String> results = new ArrayList<>();
        char[] result = new char[n];
        helper(results, result, 0, n - 1);
        return results;
    }
    
    private void helper(List<String> results, char[] result, int i, int j) {
        if (i > j) {
            results.add(new String(result));
        } else if (i == j) {
            for (char c : SINGLES) {
                result[i] = c;
                helper(results, result, i + 1, j - 1);
            }
        } else {
            // i < j
            for (char[] pair : PAIRS) {
                if (i == 0 && pair[0] == '0') {
                    continue;
                }
                result[i] = pair[0];
                result[j] = pair[1];
                helper(results, result, i + 1, j - 1);
            }
        }
    }
}
