package leetcode.sorting;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumEqualsK_325 {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (!map.containsKey(sum)) {
                // Could have duplicates
                map.put(sum, i);
            }
            
            if (sum == k) {
                maxLen = i + 1;
            } else if (map.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - map.get(sum - k));
            }
        }
        return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
    }
}
