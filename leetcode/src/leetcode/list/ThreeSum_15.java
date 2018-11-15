package leetcode.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) {
                    l++;
                } else if (sum > 0 || r < nums.length - 2 && nums[r] == nums[r + 1]) {
                    r--;
                } else {
                    results.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (++l < r && nums[l] == nums[l - 1]);
                    while (l < --r && nums[r] == nums[r + 1]);
                }
            }
        }
        return results;
    }
}
