package leetcode.sorting;

import java.util.ArrayDeque;
import java.util.Deque;

public class KEmptySlots_683 {
    public int kEmptySlots(int[] flowers, int k) {
        return slidingWindowSol(flowers, k);
    }
    
    private int slidingWindowSol(int[] flowers, int k) {
        if (flowers == null || flowers.length == 0) {
            return -1;
        }
        int n = flowers.length;
                                 // flowers[i]: on day i, flower[i] blooms
        int[] days = new int[n]; // days[i]: on day days[i], flower i blooms
        for (int i = 0; i < n; i++) {
            days[flowers[i] - 1] = i;
        }
        
        int ans = Integer.MAX_VALUE;
        int left = 0, right = left + k + 1;
        while (right < n) {
            boolean validCandidate = true;
            for (int pos = left + 1; pos < right; pos++) {
                if (days[pos] < days[left] || days[pos] < days[right]) {
                    left = pos;
                    right = left + k + 1;
                    validCandidate = false;
                    break;
                }
            }
            if (validCandidate) {
                ans = Math.min(ans, Math.max(days[left], days[right]) + 1);
                left = right;
            }
            right = left + k + 1;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    class MinQueue {
        private Deque<Integer> windowMins = new ArrayDeque<>();
        private Deque<Integer> window = new ArrayDeque<>();
        
        public void addLast(int x) {
            window.addLast(x);
            while (!windowMins.isEmpty() && windowMins.peekLast() > x) {
                windowMins.pollLast();
            }
            windowMins.addLast(x);
        }
        
        public void pollFirst() {
            if (window.isEmpty()) {
                return;
            }
            int x = window.pollFirst();
            if (x == windowMins.peekFirst()) {
                windowMins.pollFirst();
            }
        }
        
        public int min() {
            return windowMins.size() == 0 ? -1 : windowMins.peekFirst();
        }
    };
    
    private int minQueueSol(int[] flowers, int k) {
        if (flowers == null || flowers.length == 0) {
            return -1;
        }
        int n = flowers.length;
                                 // flowers[i]: on day i, flower[i] blooms
        int[] days = new int[n]; // days[i]: on day days[i], flower i blooms
        for (int i = 0; i < n; i++) {
            days[flowers[i] - 1] = i;
        }
        
        int day = Integer.MAX_VALUE;
        MinQueue minQueue = new MinQueue();
        for (int pos = 1; pos < n; pos++) {
            if (pos > k) {
                if (k == 0 || days[pos - k - 1] < minQueue.min() && days[pos] < minQueue.min()) {
                    // Find the earliest day this happens
                    day = Math.min(day, Math.max(days[pos - k - 1], days[pos]) + 1);
                }
                minQueue.pollFirst();
            }   
            minQueue.addLast(days[pos]);
        }
        return day == Integer.MAX_VALUE ? -1 : day;
    }
}
