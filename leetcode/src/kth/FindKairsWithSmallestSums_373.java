package kth;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKairsWithSmallestSums_373 {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return new ArrayList<>(0);
        }
        
        int length1 = Math.min(nums1.length, k);
        int length2 = Math.min(nums2.length, k);
        PriorityQueue<Tuple> pq = new PriorityQueue<>(length2);
        for (int j = 0; j < length2; j++) {
            pq.offer(new Tuple(0, j, nums1[0] + nums2[j]));
        }
        
        List<int[]> sumPairs = new ArrayList<>(k);
        int kk = 0;
        while (kk++ < k && !pq.isEmpty()) {
            Tuple tuple = pq.poll();
            sumPairs.add(new int[] {nums1[tuple.x], nums2[tuple.y]});
            
            if (tuple.x < length1 - 1) {
                pq.offer(new Tuple(tuple.x + 1, tuple.y, nums1[tuple.x + 1] + nums2[tuple.y]));
            }
        }
        return sumPairs;
    }
    
    class Tuple implements Comparable<Tuple> {
        int x, y, val;
        
        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        
        @Override
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }
}
