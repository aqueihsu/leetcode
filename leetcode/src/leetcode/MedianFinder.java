package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    private PriorityQueue<Integer> large = new PriorityQueue<>();
    private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
    
    /** initialize your data structure here. */
    public MedianFinder() {
    }
    
    public void addNum(int num) {
        large.add(num);
        small.add(large.poll());
        if (large.size() < small.size()) {
            large.add(small.poll());
        }
    }
    
    public double findMedian() {
        if (large.size() == small.size()) {
            return (large.peek() + small.peek()) / 2.0;
        }
        return large.peek();
    }
}
