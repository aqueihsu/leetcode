package leetcode.sorting;


public class KthLargestElementInAnArray_215 {
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        int start = 0, end = nums.length - 1;
        int index;
        while ((index = partition(nums, start, end)) != k - 1) {
            if (index < k - 1) {
                start = index + 1;
            } else {
                end = index - 1;
            }
        }
        return nums[k - 1];
    }
    
    // Corner cases!!!
    private int partition(int[] nums, int start, int end) {
        int p = nums[start];
        int s = start + 1, e = end;
        while (s <= e) {
            if (nums[s] < p && nums[e] > p) {
                swap(nums, s++, e--);
            }
            if (nums[s] >= p) {
                s++;
            } 
            if (nums[e] <= p) {
                e--;
            }
        }
        swap(nums, start, e);
        return e;
    }
    
    private void swap(int nums[], int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
