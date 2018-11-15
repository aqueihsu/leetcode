package leetcode.sorting;

// Could use counting sort
public class SortColors_75 {
    public void sortColors(int[] nums) {
        final int white = 1;
        int n = nums.length;
        int i = 0, j = 0, k = n - 1;
        while (j <= k) {
            if (nums[j] < white) {
                swap(nums, i++, j++);
            } else if (nums[j] > white) {
                swap(nums, j, k--);
            } else {
                j++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
