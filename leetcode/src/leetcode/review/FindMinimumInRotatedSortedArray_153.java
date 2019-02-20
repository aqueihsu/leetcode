package leetcode.review;

public class FindMinimumInRotatedSortedArray_153 {
    // [3,4,5,1,2]
    // [3,1,2]
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[l] <= nums[m]) {
                // Left side is sorted
                if (nums[r] < nums[l]) {
                    l = m + 1;
                } else {
                    break;
                }
            } else {
                // nums[m] < nums[r]
                // Right side is sorted
                r = m;
            }
        }
        return nums[l];
    }
}
