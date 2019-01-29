package leetcode.dp;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings_317 {
    private static final int[][] DIRECTIONS = {{1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int[][] reachable;  // Whether the "0" is reachable from the i-th building, to reduce search space
    private int[][] sum;
    
    public int shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                reachable[i][j] = grid[i][j] == 0 ? -1 /* reachable by -1th building */: -2;
            }
        }
        
        int[] pos = new int[2];
        int iBuilding = 0;
        for (int i = 0; i < m; i++, pos[0] = i) {
            for (int j = 0; j < m; j++, pos[1] = j) {
                bfs(reachable, pos, iBuilding++);
            }
        }
    }
    
    // Use bfs to figure out the shortest distance from all "0"s to the "1"
    private int[][] bfs(int[][] reachable, int[] pos, int iBuilding) {
        int m = reachable.length, n = reachable[0].length;
        int[][] distance = new int[m][n];
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        // TODO: Add to the sum here
    }
}
