package leetcode.review;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis_22 {
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        if (n > 0) {
            helper(n, n, 0, new char[n << 1], results);
        }
        return results;
    }
    
    // Remaining left and remaining right
    private void helper(int left, int right, int i, char[] result, List<String> results) {
        if (left == 0 && right == 0) {
            results.add(new String(result));
            return;
        }
        if (left > 0) {
            result[i] = '(';
            helper(left - 1, right, i + 1, result, results);
        }
        if (left < right) {
            result[i] = ')';
            helper(left, right - 1, i + 1, result, results);
        }
    }
}
