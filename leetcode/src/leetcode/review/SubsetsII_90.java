package leetcode.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SubsetsII_90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums.length > 0) {
            Arrays.sort(nums);
            helper(nums, 0, new Stack<Integer>(), results);
        }
        return results;
    }
    
    private void helper(int[] nums, int pos, Stack<Integer> result, List<List<Integer>> results) {
        if (pos == nums.length) {
            results.add(new ArrayList<>(result));
            return;
        }
        // No need to loop through each element, not permutation!
        // while (pos < nums.length) {
        {
            int count = 0, num = nums[pos];
            for (; pos < nums.length && num == nums[pos]; pos++) {
                count++;
            }
            // Say we have three 5's, then it's used as zero/one/two/three 5's
            helper(nums, pos, result, results);
            for (int i = 0; i < count; i++) {
                result.push(num);
                helper(nums, pos, result, results);
            }
            for (int i = 0; i < count; i++) {
                result.pop();
            }
        }
    }
}
