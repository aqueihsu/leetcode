package leetcode;

public class CitySkyline_807 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int[] topSkyline = new int [grid.length];
        int[] leftSkyline = new int [grid[0].length];
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Faster than topSkyline[i] = Math.max(topSkyline[i], grid[i][j])
                if (topSkyline[i] < grid[i][j]) {
                    topSkyline[i] = grid[i][j];
                }
                if (leftSkyline[j] < grid[i][j]) {
                    leftSkyline[j] = grid[i][j];
                }
            }
        }
        
        int maxIncreasedHeight = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int maxHeight = Math.min(topSkyline[i], leftSkyline[j]);
                maxIncreasedHeight += (maxHeight - grid[i][j]);
            }
        }
        return maxIncreasedHeight;
    }
}
