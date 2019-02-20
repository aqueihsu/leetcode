package leetcode.review;

public class SearchInRotatedSortedArrayII_81 {
    // [2,5,6,0,0,1,2], 0 => true
    // [1,3,1,1,1], 3 => true
    // [3,1], 1 => true
    public boolean search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }
    
    private boolean search(int[] nums, int left, int right, int target) {
        if (left > right) {
            return false;
        }
        if (left == right) {
            return nums[left] == target;
        }
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return true;
        }
        if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
            return search(nums, mid + 1, right - 1, target)
                    || search(nums, left + 1, mid - 1, target);
        }
        if (nums[left] <= nums[mid]) {
            // Left is sorted
            if (target < nums[left] || nums[mid] < target) {
                return search(nums, mid + 1, right, target);
            } else {
                // target < nums[mid] 
                return search(nums, left, mid - 1, target);
            }
        } else {
            // nums[mid] <= nums[right]
            // Right is sorted
            if (target < nums[mid] || nums[right] < target) {
                return search(nums, left, mid - 1, target);
            } else {
                // nums[mid] < target
                return search(nums, mid + 1, right, target);
            }
        }
    }
}
