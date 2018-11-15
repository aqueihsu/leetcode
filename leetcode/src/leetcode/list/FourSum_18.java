package leetcode.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum_18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        Arrays.sort(nums);
        
        helper(nums, target, 0, 4, new Integer[4], results);
        return results;
    }
    
    private void helper(int[] nums, int target, int i, int N,
            Integer[] result, List<List<Integer>> results) {
        if (nums[i] * N > target || nums[nums.length - 1] * N < target) {
            return;
        }
        if (N == 2) {
            int l = i, r = nums.length - 1;
            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum < target) {
                    l++;
                } else if (sum > target) {
                    r--;
                } else {
                    // sum == target
                    result[1] = nums[l];
                    result[0] = nums[r];
                    results.add(new ArrayList<>(Arrays.asList(result)));
                    
                    while (++l < r && nums[l] == nums[l - 1]);
                }
            }
            
        } else {
            for (int j = i; j <= nums.length - N; j++) {
                if (j == i || nums[j] != nums[j - 1]) {
                    result[N - 1] = nums[j];
                    helper(nums, target - nums[j], j + 1, N - 1, result, results);
                }
            }
        }
    }
}
