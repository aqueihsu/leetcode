package leetcode.list;

public class CircularArrayLoop_457 {
    public boolean circularArrayLoop(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            int slow = i, fast = i;
            boolean forward = nums[slow] > 0;
            do {
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
                if (slow == fast) {
                    if (slow == next(nums, slow) || forward != nums[slow] > 0) {
                        // Single element loop
                        break;
                    }
                    return true;
                }
            } while (forward == nums[slow] > 0);
        }
        return false;
    }
    public boolean circularArrayLoop1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            // Detect loop
            int slow = i, fast = i;
            do {
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            } while (fast != slow);
            
            // Find the beginning of loop
            slow = i;
            while (slow != fast) {
                slow = next(nums, slow);
                fast = next(nums, fast);
            }
            
            // Find the size of the loop
            int loopSize = 0;
            boolean forward = nums[slow] > 0;
            do {
                loopSize++;
                slow = next(nums, slow);
                if (forward != nums[slow] > 0) {
                    return false;
                }
            } while (slow != fast);
            if (loopSize > 1) {
                return true;
            }
        }
        return false;
    }
    
    private int next(int[] nums, int i) {
        int nextIdx = (nums[i] + i) % nums.length;
        return nextIdx < 0 ? nums.length + nextIdx : nextIdx;
    }
}
