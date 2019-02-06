package backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis_22 {
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        helper(results, new char[n << 1], 0, 0, n);
        return results;
    }
    
    private void helper(List<String> results, char[] parentheses, int numOpens, int numCloses, int max) {
        if ((numCloses + numOpens) == (max << 1)) {
            results.add(new String(parentheses));
            return;
        }
        
        // Either put an open or a close at this position, when valid
        int curPos = numCloses + numOpens;
        if (numOpens < max) {
            parentheses[curPos] = '(';
            helper(results, parentheses, numOpens + 1, numCloses, max);
        }
        if (numOpens > numCloses) {
            parentheses[curPos] = ')';
            helper(results, parentheses, numOpens, numCloses + 1, max);
        }
    }
}
