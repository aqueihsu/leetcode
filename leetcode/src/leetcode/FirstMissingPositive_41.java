package leetcode;

// Very tricky boundary cases
public class FirstMissingPositive_41 {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num, pos;
            while ((num = nums[i]) > 0 && num < nums.length && nums[pos = num - 1] != nums[i]) {
                swap(nums, i, pos);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i + 1)) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
