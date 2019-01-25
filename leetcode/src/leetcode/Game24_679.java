package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game24_679 {
    // Math.abs(result - 24) < 1e-6 for double compare
    private static final char[] OPS = {'+', '-', '*', '/'};
    
    public boolean judgePoint24(int[] nums) {
        double[] doubleNums = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            doubleNums[i] = nums[i];
        }
        return dfsOrdering(doubleNums, new boolean[nums.length], new double[nums.length], 0);
    }
    
    private boolean dfsOrdering(double[] nums, boolean[] visited, double[] orderedNums, int j) {
        if (j == nums.length) {
            List<Double> results = dfsGrouping(orderedNums, 0, orderedNums.length - 2);
             
            for (double result : results) {
                if (Math.abs(result - 24) < 1e-6) {
                    return true;
                }
            }
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                orderedNums[j] = nums[i];
                visited[i] = true;
                if (dfsOrdering(nums, visited, orderedNums, j + 1)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
    
    // end: inclusive
    private List<Double> dfsGrouping(double[] nums, int start, int end) {
        if (start > end) {
            return Arrays.asList(nums[start]);
        }
        List<Double> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<Double> leftResults = dfsGrouping(nums, start, i - 1);
            List<Double> rightResults = dfsGrouping(nums, i + 1, end);
            for (char op : OPS) {
                for (double left : leftResults) {
                    for (double right : rightResults) {
                        results.add(evaluate(op, left, right));
                    }
                }
            }
        }
        return results;
    }
    
    private double evaluate(char op, double val1, double val2) {
        switch (op) {
        case '+': return val1 + val2;
        case '-': return val1 - val2;
        case '*': return val1 * val2;
        case '/': return val2 == 0 ? 0 : val1 / val2;
        default: return 0;
        }
    }
}