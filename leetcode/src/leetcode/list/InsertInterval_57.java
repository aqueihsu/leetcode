package leetcode.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval_57 {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.isEmpty()) {
            return Arrays.asList(newInterval);
        }
        List<Interval> results = new ArrayList<Interval>();
        int start = newInterval.start, end = newInterval.end;
        int latest = Integer.MIN_VALUE;
        for (Interval intv : intervals) {
            // THIS!
            if (intv.start > end && latest < start) {
                results.add(new Interval(start, end));
            }
            if ((intv.end < start || intv.start > end || (intv.start < start && intv.end > end))) {
                results.add(intv);
                latest = intv.end;
            } else {
                start = Math.min(start, intv.start);
                end = Math.max(end, intv.end);
            }
        }
        if (latest < start) {
            results.add(new Interval(start, end));
        }
        return results;
    }
}
