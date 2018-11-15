package leetcode.dfsbfs;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators_282 {
    public List<String> addOperators(String num, int target) {
        List<String> results = new ArrayList<>();
        if (num.length() == 0) {
            return results;
        }
        char[] result = new char[num.length() << 1];
        dfs(results, result, 0 /* len */, num, 0 /* i */, 0 /* prevSum */, 0 /* prevVal */, target);
        return results;
    }
    
    private void dfs(List<String> results, char[] result, int len,
            String num, int pos,
            long prevSum, long prevVal, long target) {
        if (pos == num.length()) {
            if (prevSum == target) {
                results.add(String.valueOf(result, 0 /* offset */, len));
            }
            return;
        }
        
        int endIndex = num.charAt(pos) == '0' ? pos + 1 : num.length();
        int iOp = (pos == 0) ? len : len++;
        long val = 0;
        for (int i = pos; i < endIndex; i++) {
            char c = num.charAt(i);
            
            result[len++] = c;
            val = val * 10 + c - '0';
            
            if (pos == 0) {
                // This is cleaner but slows down the perf
                dfs(results, result, i + 1 /* len */, num, i + 1 /* i */, val /* prevSum */, val /* prevVal */, target);
            } else {
                result[iOp] = '+';
                dfs(results, result, len,  num, i + 1,  prevSum + val, val, target);
                
                result[iOp] = '-';
                dfs(results, result, len,  num, i + 1,  prevSum - val, -val, target);
                
                result[iOp] = '*';
                dfs(results, result, len,  num, i + 1,  prevSum + prevVal * (val - 1), prevVal * val, target);
            }
        }
    }
}
