package leetcode.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TheSkylineProblem_218 {
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int HEIGHT = 2;
    
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> criticalPts = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (x, y) -> (x[HEIGHT] == y[HEIGHT]) ? x[LEFT] - y[LEFT] : y[HEIGHT] - x[HEIGHT]);
        
        int curBldg = 0;
        int curX = 0;
        while (curBldg < buildings.length || !queue.isEmpty()) {
            if (queue.isEmpty() || curBldg < buildings.length && buildings[curBldg][LEFT] <= queue.peek()[RIGHT]) {
                // Need to add current building if it overlaps with the tallest building so far
                curX = buildings[curBldg][LEFT];
                while (curBldg < buildings.length && buildings[curBldg][LEFT] == curX) {
                    queue.add(buildings[curBldg++]);
                }
            } else {
                // Otherwise removes dead buildings
                curX = queue.peek()[RIGHT];
                while (!queue.isEmpty() && queue.peek()[RIGHT] <= curX) {
                    queue.poll();
                }
            }
            
            int curH = queue.isEmpty() ? 0 : queue.peek()[HEIGHT];
            if (criticalPts.isEmpty() || curH != criticalPts.get(criticalPts.size() - 1)[1]) {
                criticalPts.add(new int[] {curX, curH});
            }
        }
        return criticalPts;
    }
}
