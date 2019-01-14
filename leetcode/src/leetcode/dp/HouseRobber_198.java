package leetcode.dp;

public class HouseRobber_198 {
    public int rob(int[] nums) {
        // return recursion(0, nums);
        
        /*
        int[] maxAmts = new int[nums.length];
        Arrays.fill(maxAmts, -1);
        return recursionWithMemo(0, maxAmts, nums);
        */
        return iterativeWithMemo(nums);
    }
    
    private int recursion(int iCurHouse, int[] nums) {
        if (iCurHouse >= nums.length) {
            return 0;
        }
        int amtRob = recursion(iCurHouse + 2, nums) + nums[iCurHouse];
        int amtNoRob = recursion(iCurHouse + 1, nums);
        return Math.max(amtRob, amtNoRob);
    }
    
    private int recursionWithMemo(int iCurHouse, int[] maxAmts, int[] nums) {
        if (iCurHouse >= nums.length) {
            return 0; // The base case
        }
        if (maxAmts[iCurHouse] != -1) {
            return maxAmts[iCurHouse];
        }
        int amtRob = recursionWithMemo(iCurHouse + 2, maxAmts, nums) + nums[iCurHouse];
        int amtNoRob = recursionWithMemo(iCurHouse + 1, maxAmts, nums);
        return maxAmts[iCurHouse] = Math.max(amtRob, amtNoRob);
    }
    
    private int iterativeWithMemo(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0]; // Don't forget this check!
        }
        int[] maxAmts = new int[nums.length + 1];
        maxAmts[0] = 0;
        maxAmts[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxAmts[i + 1] = Math.max(maxAmts[i], maxAmts[i - 1] + nums[i]);
        }
        return maxAmts[nums.length];
        
        // Can further reduce this to two variables
    }
}
