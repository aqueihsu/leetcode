package leetcode.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import leetcode.sorting.MergeIntervals_56.Interval;

public class MeetingRoomsII_253 {
    class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals.length;
        }
        List<Interval> orderedByStart = Arrays.asList(intervals);
        Collections.sort(orderedByStart, (a, b) -> {return a.start - b.start;});
        
        int maxNumRooms = 0;
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> {return a.end - b.end;});
        for (Interval interval : orderedByStart) {
            if (!minHeap.isEmpty() && interval.start >= minHeap.peek().end ) {
                minHeap.poll();
            }
            minHeap.add(interval);
            maxNumRooms = Math.max(maxNumRooms, minHeap.size());
        }
        return maxNumRooms;
    }
}
