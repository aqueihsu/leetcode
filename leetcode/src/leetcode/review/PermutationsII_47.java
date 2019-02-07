package leetcode.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PermutationsII_47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums.length > 0) {
            Arrays.sort(nums);
            helper(new boolean[nums.length], nums, new Stack<Integer>(), results);
        }
        return results;
    }
    
    private void helper(boolean[] visited, int[] nums, Stack<Integer> result, List<List<Integer>> results) {
        if (result.size() == nums.length) {
            results.add(new ArrayList<>(result));
        }
        boolean first = true;
        int prevNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i] && (first || nums[i] != prevNum)) {
                first = false;
                visited[i] = true;
                result.push(nums[i]);
                helper(visited, nums, result, results);
                result.pop();
                visited[i] = false;
                prevNum = nums[i];
            }
        }
    }
}
