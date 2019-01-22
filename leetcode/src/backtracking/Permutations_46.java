package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations_46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        helper(nums, new boolean[nums.length], new ArrayList<>(nums.length), results);
        return results;
    }
    
    private void helper(int[] nums, boolean[] used, List<Integer> result, List<List<Integer>> results) {
        if (result.size() == nums.length) {
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                result.add(nums[i]);
                used[i] = true;
                helper(nums, used, result, results);
                result.remove(result.size() - 1);
                used[i] = false;
            }
        }
    }
}
