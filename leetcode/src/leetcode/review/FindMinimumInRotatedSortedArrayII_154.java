package leetcode.review;

public class FindMinimumInRotatedSortedArrayII_154 {
    // [1,3,5]
    // [2,2,2,0,1]
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }
    
    private int findMin(int[] nums, int l, int r) {
        if (l > r) {
            return Integer.MAX_VALUE;
        }
        if (l == r) {
            return nums[l];
        }
        int m = (l + r) / 2;
        if (nums[l] == nums[m] && nums[m] == nums[r]) {
            return Math.min(findMin(nums, l, m - 1), findMin(nums, m + 1, r));
        }
        if (nums[l] <= nums[m]) {
            // Left side is sorted
            if (nums[r] <= nums[l]) {
                return findMin(nums, m + 1, r);
            } else {
                // nums[l] < nums[r]
                return nums[l];
            }
        } else {
            // nums[m] <= nums[r]
            // Right side is sorted
            return findMin(nums, l, m);
        }
    }
}
