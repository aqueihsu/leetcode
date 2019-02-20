package leetcode.review;

import java.util.ArrayList;
import java.util.Arrays;

public class FindKthSmallestPairDistance_719 {
    // 1, 2, 3, 4, 5, 6, 7 
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        
        int n = nums.length;
        int l = 0;
        int r = nums[n - 1] - nums[0];
        
        while (l <= r) {
            int dist = (l + r) / 2;
            int count = 0;
            for (int i = 0, j = 1; i < n; i++) {
                while (j < n && nums[i] + dist >= nums[j]) {
                    j++;
                }
                count += j - i - 1; // j - i numbers, j - i - 1 pairs
            }
            
            if (count >= k) {
                // Find the left most
                r = dist;
            } else {
                l = dist + 1;
            }
        }
        return l;
    }
}
