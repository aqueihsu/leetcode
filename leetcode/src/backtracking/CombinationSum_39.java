package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new LinkedList<>();
        helper(results, new LinkedList<Integer>(), candidates, target, 0);
        return results;
    }
    
    // Backtracking
    private void helper(List<List<Integer>> results, List<Integer> curResult, final int[] candidates, int target, int pos) {
        if (target == 0) {
            results.add(new ArrayList<>(curResult));
            return;
        }
        
        for (int i = pos; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                curResult.add(candidates[i]);
                helper(results, curResult, candidates, target - candidates[i], i);
                curResult.remove(curResult.size() - 1);
            }
        }
    }
}
