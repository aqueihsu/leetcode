package leetcode.dp;

public class HouseRobberII_213 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // return Math.max(recursion(2, true, nums) + nums[0], recursion(1, false, nums));
        
        /*
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        int result = recursionWithMemo(2, true, memo, nums) + nums[0];
        
        Arrays.fill(memo, -1);
        return Math.max(result, recursionWithMemo(1, false, memo, nums));
        */
        return topdownWithMemo(nums);
    }
    
    // Can use low/high to limit the compuation space too
    private int recursion(int iCurHouse, boolean firstIncluded, int[] nums) {
        if (iCurHouse >= nums.length) {
            return 0;
        }
        if (iCurHouse == nums.length - 1) {
            return firstIncluded ? 0 : nums[nums.length - 1];
        }
        int amtRob = recursion(iCurHouse + 2, firstIncluded, nums) + nums[iCurHouse];
        int amtNoRob = recursion(iCurHouse + 1, firstIncluded, nums);
        return Math.max(amtRob, amtNoRob);
    }
    
    private int recursionWithMemo(int iCurHouse, boolean firstIncluded, int[] memo, int[] nums) {
        if (iCurHouse >= nums.length) {
            return 0;
        }
        if (iCurHouse == nums.length - 1) {
            return firstIncluded ? 0 : nums[nums.length - 1];
        }
        if (memo[iCurHouse] > -1) {
            return memo[iCurHouse];
        }
        int amtRob = recursionWithMemo(iCurHouse + 2, firstIncluded, memo, nums) + nums[iCurHouse];
        int amtNoRob = recursionWithMemo(iCurHouse + 1, firstIncluded, memo, nums);
        return memo[iCurHouse] = Math.max(amtRob, amtNoRob);
    }
    
    private int topdownWithMemo(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] memo = new int[nums.length + 1];
        
        // Include head
        memo[1] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            memo[i + 1] = Math.max(memo[i - 1] + nums[i], memo[i]);
        }
        int result = memo[nums.length - 1];
        
        // Not include head
        memo[1] = 0;
        for (int i = 1; i < nums.length; i++) {
            memo[i + 1] = Math.max(memo[i - 1] + nums[i], memo[i]);
        }
        return Math.max(result, memo[nums.length]);
    }
}
