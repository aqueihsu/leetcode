package leetcode.dp;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings_317 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    int m, n;
    private int[][] reachable;  // Whether the "0" is reachable from the i-th building, to reduce search space
    private int[][] sum;
    
    public int shortestDistance(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        reachable = new int[m][n];
        sum = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                reachable[i][j] = grid[i][j] == 0 ? 0 /* reachable by -1th building */: -1;
            }
        }
        int[] building = new int[2];
        int iBuilding = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    building[0] = i;
                    building[1] = j;
                    bfs(building, iBuilding++);
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (reachable[i][j] == iBuilding) {
                    minDistance = Math.min(minDistance, sum[i][j]);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
    
    // Use bfs to figure out the shortest distance from all "0"s to the "1"
    private void bfs(int[] building, int iBuilding) {
        int[][] distance = new int[m][n];
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(building);
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            int curDistance = distance[x][y];
            
            for (int[] direction : DIRECTIONS) {
                int nextX = x + direction[0];
                int nextY = y + direction[1];
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                    if (reachable[nextX][nextY] == iBuilding) {
                        reachable[nextX][nextY]++;
                        distance[nextX][nextY] = curDistance + 1;
                        sum[nextX][nextY] += distance[nextX][nextY];
                        queue.add(new int[] {nextX, nextY});
                    }
                }
            }
        }
    }
}
