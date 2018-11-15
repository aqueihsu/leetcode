package kth;

import java.util.Arrays;

public class KthSmallestPairDistance {
    // Sol1: sort the input array. Then it's essentially finding the kth smallest d_ij
    //       within a 2D array
    // Sol2: trial and error solution
    //       https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109082/Approach-the-problem-using-the-%22trial-and-error%22-algorithm
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        
        int n = nums.length;
        int l = 0;
        int r = nums[n - 1] - nums[0];
        
        for (int cnt = 0; l < r; cnt = 0) {
            int dist = (l + r)/2;
            
            for (int i = 0, j = 1; i < n; i++) {
                while (j < n && (nums[j] <= nums[i] + dist)) {
                    j++;
                }
                cnt += j - i - 1;
            }
            
            if (cnt < k) {
                l = dist + 1;
            } else {
                r = dist;
            }
        }
        return l;
    }
}
