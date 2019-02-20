package leetcode.review;

public class KthLargestElementInAnArray_215 {
    // [3,2,3,1,2,4,5,5,6], 4
    //  0 1 2 3 4 5 6 7 8
    // [2,1], 1
    public int findKthLargest(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        int pivot = l;
        k = nums.length - k;
        while ((pivot = helper(nums, l, r)) != k) {
            if (pivot < k) {
                l = pivot + 1;
            } else {
                r = pivot - 1;
            }
        }
        return nums[k];
    }
    
    private int helper(int[] nums, int l, int r) {
        int pivot = l++;
        while (l <= r) {
            while (l <= r && nums[l] <= nums[pivot]) {
                l++;
            }
            while (l <= r && nums[r] > nums[pivot]) {
                r--;
            }
            if (l < r) {
                swap(nums, l++, r--);
            }
        }
        swap(nums, --l, pivot);
        return l;
    }
    
    private void swap(int[] nums, int l, int r) {
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
    }
}
