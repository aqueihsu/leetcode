package backtracking;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class ReachingPoints_780 {
    // Time Limit Exceeded
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        Deque<long[]> queue = new LinkedList<>();
        queue.add(new long[] {sx, sy});
        while (!queue.isEmpty()) {
            long[] pair = queue.getFirst();
            if (pair[0] == tx && pair[1] == ty) {
                return true;
            } else if (pair[0] < tx && pair[1] < ty) {
                queue.push(new long[] {pair[0], pair[0] + pair[1]});
                queue.push(new long[] {pair[0] + pair[1], pair[1]});
            }
        }
        return false;
    }
    
    // Stack overflow
    private boolean helper(long sx, long sy, long tx, long ty) {
        if (sx > tx || sy > ty) {
            return false;
        }
        return (sx == tx && sy == ty) || helper(sx, sx + sy, tx, ty) || helper(sx + sy, sy, tx, ty);
    }
}
