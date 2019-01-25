package leetcode.list;

import java.util.ArrayList;
import java.util.List;

public class RangeModule_715 {
    private class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    
    class RangeModule {
        private List<Interval> intvs = new ArrayList<>();

        public RangeModule() {
            
        }
        
        public void addRange(int left, int right) {
            List<Interval> results = new ArrayList<>();
            
            int latest = Integer.MIN_VALUE;
            for (Interval intv : intvs) {
                if (latest < left && intv.start > right) {
                    results.add(new Interval(left, right));
                }
                if (intv.start > right || intv.end < left || (intv.start < left && intv.end > right)) {
                    // No overlaps
                    results.add(intv);
                    latest = intv.end;
                } else {
                    // Overlaps, need to merge
                    left = Math.min(left, intv.start);
                    right = Math.max(right, intv.end);
                }
            }
            if (latest < left) {
                results.add(new Interval(left, right));
            }
            intvs = results;
        }
        
        public boolean queryRange(int left, int right) {
            for (Interval intv : intvs) {
                if (intv.start <= left && intv.end >= right) {
                    return true;
                }
            }
            return false;
        }
        
        public void removeRange(int left, int right) {
            List<Interval> results = new ArrayList<>();
            
            int latest = Integer.MIN_VALUE;
            for (Interval intv : intvs) {
                if (latest < left && intv.start > right) {
                    return;
                }
                if (intv.start > right || intv.end < left || (intv.start < left && intv.end > right)) {
                    // No overlaps
                    results.add(intv);
                    latest = intv.end;
                } else {
                    
                }
            }
        }
    }
}
