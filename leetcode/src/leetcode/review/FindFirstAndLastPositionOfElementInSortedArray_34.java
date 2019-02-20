package leetcode.review;

public class FindFirstAndLastPositionOfElementInSortedArray_34 {
    // [5,7,7,8,8,10]
    // [1]
    public int[] searchRange(int[] nums, int target) {
        int[] results = new int[] {-1, -1};
        if (nums.length == 0) {
            return results;
        }
        
        // Find the left end
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] < target) {
                l = m + 1;
            } else {
                // nums[m] >= target
                r = m - 1;
            }
        }
        if (r + 1 < nums.length && nums[r + 1] == target) {
            results[0] = r + 1;
        }
        
        // Find the right end
        l = 0;
        r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] <= target) {
                l = m + 1;
            } else {
                // nums[m] > target 
                r = m - 1;
            }
        }
        if (l - 1 >= 0 && nums[l - 1] == target) {
            results[1] = l - 1;
        }
        
        return results;
    }
}
