package leetcode.dp;

import java.util.Arrays;

public class MaximumSubarray_53 {
    private int maxSubArrayDP(int[] nums) {
        if (nums == null || nums.length < 0) {
            return 0;
        }
        
        int maxSum = nums[0];
        int maxSumEndingAtI = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxSumEndingAtI = maxSumEndingAtI > 0 ? maxSumEndingAtI + nums[i] : nums[i];
            maxSum = Math.max(maxSum, maxSumEndingAtI);
        }
        return maxSum;
    }
    
    public int maxSubArray(int[] nums) {
        int[] A = new int[4];
        maxSubArrayDC(nums, A, 0, nums.length - 1);
        return A[0];
    }
    
    private void maxSubArrayDC(int[] nums, int[] A, int l, int r) {
        if (nums == null || nums.length < 0) {
            return;
        }
        if (l == r) {
            Arrays.fill(A, nums[l]);
            return;
        }
        int m = (l + r) / 2;
        int[] A1 = new int[4], A2 = new int[4];
        maxSubArrayDC(nums, A1, l, m);
        maxSubArrayDC(nums, A2, m + 1, r);
        A[0] = Math.max(Math.max(A1[0], A2[0]), A2[1] + A1[2]); // mx
        A[1] = Math.max(A1[1], A1[3] + A2[1]); // lmx
        A[2] = Math.max(A2[2], A2[3] + A1[2]); // rmx
        A[3] = A1[3] + A2[3];
    }
}
