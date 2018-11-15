package leetcode.sorting;

import java.util.Iterator;
import java.util.PriorityQueue;

public class ExamRoom_855 {
    class ExamRoom {
        private class Interval {
            private int lo;
            private int hi;
            private int dist;
            public Interval(int lo, int hi) {
                this.lo = lo;
                this.hi = hi;
                this.dist = lo == -1 || hi == n ? hi - lo : ((hi - lo) / 2 + 1);
                // N = 3, (-1, 3) => dist = 4; (-1, 2), (2, 3) => dist = 3, 1 
            }
        }
        
        private final int n;
        private PriorityQueue<Interval> queue = new PriorityQueue<>(
                (a, b) -> a.dist == b.dist ? a.lo - b.lo : b.dist - a.dist);
        
        public ExamRoom(int N) {
            n = N;
            queue.add(new Interval(-1, N));
        }
        
        public int seat() {
            Interval interval = queue.peek();
            if (interval.dist == 0) {
                return -1;
            }
            int seat = -1;
            interval = queue.poll();
            if (interval.lo == -1) {
                seat = 0;
            } else if (interval.hi == n) {
                seat = n - 1;
            } else {
                seat = (interval.lo + interval.hi) / 2;
            }
            queue.add(new Interval(interval.lo, seat));
            queue.add(new Interval(seat, interval.hi));
            return seat;
        }
        
        public void leave(int p) {
            if (queue.isEmpty()) {
                return;
            }
            Interval a = null, b = null;
            
            Iterator<Interval> itor = queue.iterator();
            while (itor.hasNext()) {
                Interval interval = itor.next();
                if (interval.hi == p) {
                    a = interval;
                } else if (interval.lo == p) {
                    b = interval;
                }
            }
            queue.remove(a);
            queue.remove(b);
            queue.add(new Interval(a.lo, b.hi));
        }
    }
}
