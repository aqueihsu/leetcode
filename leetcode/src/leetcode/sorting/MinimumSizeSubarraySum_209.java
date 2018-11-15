package leetcode.sorting;

public class MinimumSizeSubarraySum_209 {
    public int minSubArrayLen(int s, int[] nums) {
        if (s <= 0 || nums == null || nums.length == 0) {
            return 0;
        }
        int minLen = Integer.MAX_VALUE;
        int begin = 0, end = 0;
        int curSum = 0;
        while (end < nums.length) {
            curSum += nums[end++];
            
            while (curSum >= s) {
                int len = end - begin;
                if (minLen > len) {
                    minLen = len;
                }
                curSum -= nums[begin++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
