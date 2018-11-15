package leetcode.diffnums;

public class FindTheDuplicateNumber_287 {
    // 1. Binary search, use mind, partition the array to two halves, go into the inbalance half
    // 2. Linked list loop head
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        fast = 0;
        do {
            slow = nums[slow];
            fast = nums[fast];
        } while (slow != fast);
        return slow;
    }
}
