package leetcode;

public class NextPermutation_31 {
    public void nextPermutation(int[] nums) {
        // 1, 1, 1, 1, 3, 5, 4, 4, 2, 1
        // 1, 1, 1, 1, 4, 
        int i = nums.length - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        
        if (i <= 0) {
            reverse(nums, 0, nums.length - 1);
        } else {
            int iHead = i - 1;
            int iSwap = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] > nums[iHead] && nums[j] <= nums[iSwap]) {
                    iSwap = j;
                }
            }
            int t = nums[iHead];
            nums[iHead] = nums[iSwap];
            nums[iSwap] = t;
            
            // No need to sort
            reverse(nums, iHead + 1, nums.length - 1);
        }
    }
    
    private void reverse(int[] nums, int start, int end) {
        int i = start;
        int j = end;
        while (i < j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }
    }
}
