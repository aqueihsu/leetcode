package leetcode.sorting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class EmployeeFreeTime_759 {
    private class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    };
    
    private class IntervalItorator {
        List<Interval> list;
        int i;
        
        IntervalItorator(List<Interval> list) {
            this.list = list;
            i = 0;
        }
        
        final Interval current() {
            return list.get(i);
        }
        
        boolean next() {
            return ++i < list.size();
        }
    }
    
    public List<Interval> employeeFreeTime(List<List<Interval>> schedules) {
        PriorityQueue<IntervalItorator> queue =
                new PriorityQueue<>((itor1, itor2) -> (itor1.current().start - itor2.current().start));
        
        for (List<Interval> schedule : schedules) {
            if (schedule.isEmpty()) {
                continue;
            }
            queue.add(new IntervalItorator(schedule));
        }
        
        List<Interval> freeIntervals = new ArrayList<>();
        int end = queue.peek().current().end;
        while (!queue.isEmpty()) {
            IntervalItorator itor = queue.poll();
            Interval interval = itor.current();
            
            if (interval.start > end) {
                // Found a common free time interval
                freeIntervals.add(new Interval(end, interval.start));
            }
            end = Math.max(end, interval.end);
            if (itor.next()) {
                queue.add(itor);
            }
        }
        return freeIntervals;
    }
}
