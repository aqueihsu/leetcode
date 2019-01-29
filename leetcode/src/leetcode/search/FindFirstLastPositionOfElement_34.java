package leetcode.search;

public class FindFirstLastPositionOfElement_34 {
    public int[] searchRange(int[] nums, int target) {
        int[] results = new int[2];
        results[0] = results[1] = -1;
        
        if (nums.length == 0) {
            return results;
        }
        
        // Find the last position
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] <= target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        if (l - 1 >= 0 && l - 1 < nums.length && nums[l - 1] == target) {
            results[1] = l - 1;
        } else {
            return results;
        }
        
        // Find the first position
        l = 0; r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        if (l >= 0 && l < nums.length && nums[l] == target) {
            results[0] = l;
        }
        return results;
    }
}
