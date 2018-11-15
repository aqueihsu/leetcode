package leetcode.sorting;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    private class MaxQueue {
        Deque<Integer> max;
        Deque<Integer> queue;   // Don't really need this
        
        public MaxQueue(int k) {
            max = new ArrayDeque<>(k);
            queue = new ArrayDeque<>(k);
        }
        
        public void pollFirst() {
            int x = queue.pollFirst();
            if (!max.isEmpty() && max.peekFirst() == x) {
                max.pollFirst();
            }
        }
        
        public void addLast(int x) {
            queue.addLast(x);
            while (!max.isEmpty() && x > max.peekLast()) {
                max.pollLast();
            }
            max.addLast(x);
        }
        
        public int getMax() {
            return max.peekFirst();
        }
    };
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        
        int[] results = new int[nums.length - k + 1];
        MaxQueue maxQueue = new MaxQueue(k);
        for (int i = 0; i < nums.length; i++) {
            maxQueue.addLast(nums[i]);
            if (i > k - 2) {
                results[i - k + 1] = maxQueue.getMax();
                maxQueue.pollFirst();
            }
        }
        return results;
    }
}
