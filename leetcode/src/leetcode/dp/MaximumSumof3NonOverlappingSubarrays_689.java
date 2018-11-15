package leetcode.dp;

public class MaximumSumof3NonOverlappingSubarrays_689 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if (nums == null || nums.length <= 0) {
            return new int[3];
        }
        
        int n = nums.length;
        int[] sums = new int[n - k + 1];
        for (int j = 0; j < k; j++) {
            sums[0] += nums[j];
        }
        for (int i = 1; i <= n - k; i++) {
            sums[i] = sums[i - 1] - nums[i - 1] + nums[i + k - 1];
        }
        
        int[] rightMax = new int[n - k + 1];
        for (int i = n - k, iCurMax = n - k; i >= 0; i--) {
            if (sums[iCurMax] <= sums[i]) {
                iCurMax = i;
            }
            rightMax[i] = iCurMax;
        }
        
        int maxSum = -1;
        int[] iMaxEntries = new int[3];
        for (int i = 0, iCurMax = 0; i <= n - ((k << 1) + k); i++) {
            if (sums[iCurMax] < sums[i]) {
                iCurMax = i;
            }
            int sum = sums[iCurMax] + sums[i + k] + sums[rightMax[i + (k << 1)]];
            if (maxSum < sum) {
                maxSum = sum;
                iMaxEntries[0] = iCurMax;
                iMaxEntries[1] = i + k;
                iMaxEntries[2] = rightMax[i + (k << 1)];
            }
        }
        return iMaxEntries;
    }
}
