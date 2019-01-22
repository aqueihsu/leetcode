package leetcode.sorting;

import java.util.PriorityQueue;

public class TrappingRainWaterII_407 {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        int n = heightMap.length;
        int m = heightMap[0].length;
        
        PriorityQueue<Cell> queue = new PriorityQueue<>((c1, c2) -> (c1.height - c2.height));
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            visited[i][0] = visited[i][m - 1] = true;
            queue.offer(new Cell(i, 0, heightMap[i][0]));
            queue.offer(new Cell(i, m - 1, heightMap[i][m - 1]));
        }
        for (int j = 0; j < m; j++) {
            visited[0][j] = visited[n - 1][j] = true;
            queue.offer(new Cell(0, j, heightMap[0][j]));
            queue.offer(new Cell(n - 1, j, heightMap[n - 1][j]));
        }
        
        int trapped = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            
            for (int[] dir : dirs) {
                int x = cell.x + dir[0];
                int y = cell.y + dir[1];
                if (x >= 0 && y >= 0 && x < n && y < m && !visited[x][y]) {
                    visited[x][y] = true;
                    
                    int height = heightMap[x][y];
                    if (cell.height > height) {
                        trapped += (cell.height - height);
                        height = cell.height;
                    }
                    queue.add(new Cell(x, y, height));
                }
            }
        }
        return trapped;
    }
    
    private class Cell {
        int x;
        int y;
        int height;
        
        Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
