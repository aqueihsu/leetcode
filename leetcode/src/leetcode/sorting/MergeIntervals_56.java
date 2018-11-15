package leetcode.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeIntervals_56 {
    class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> orderedByStart = new ArrayList<>(intervals);
        Collections.sort(orderedByStart, (a, b) -> {return a.start - b.start;});
        
        List<Interval> mergedIntervals = new ArrayList<>();
        
        int i = 0;
        while (i < intervals.size()) {
            Interval curInterval = new Interval(orderedByStart.get(i).start, orderedByStart.get(i).end);
            while (i < intervals.size() && orderedByStart.get(i).start <= curInterval.end) {
                curInterval.end = Math.max(curInterval.end, orderedByStart.get(i).end);
                i++;
            }
            mergedIntervals.add(curInterval);
        }
        return mergedIntervals;
    }
}
