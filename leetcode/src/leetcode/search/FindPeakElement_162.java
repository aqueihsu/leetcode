package leetcode.search;

public class FindPeakElement_162 {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        return helper(nums, 0, nums.length - 1);
    }
    
    private int helper(int[] nums, int l, int r) {
        if (l >= r) {
            return l;
        }
        int mid1 = (l + r) / 2;
        int mid2 = mid1 + 1;
        
        if (nums[mid1] > nums[mid2]) {
            return helper(nums, l, mid1);
        }
        return helper(nums, mid2, r);
    }
}
