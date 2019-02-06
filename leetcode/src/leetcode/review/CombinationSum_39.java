package leetcode.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CombinationSum_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates.length >= 0 && target >= 0) {
            helper(target, candidates, 0, new Stack<Integer>(), results);
        }
        return results;
    }
    
    private void helper(int target, int[] candidates, int i, Stack<Integer> result, List<List<Integer>> results) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            results.add(new ArrayList<>(result));
            return;
        }
        for (int ii = i; ii < candidates.length; ii++) {
            result.push(candidates[ii]);
            helper(target - candidates[ii], candidates, ii, result, results);
            result.pop();
        }
    }
}
