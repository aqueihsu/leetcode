package leetcode.dp;

import java.util.Arrays;

public class MaximalRectangle_85 {
    // height[j]: the height of connected 1s up till this row
    // left[j]: the left most point from this point that has 1s of height[j]
    // right[j]: the right most point from this point that has 1s of height[j]
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        int nRows = matrix.length, nCols = matrix[0].length;
        int[] left = new int[nCols], right = new int[nCols], height = new int[nCols];
        Arrays.fill(right, Integer.MAX_VALUE);
        Arrays.fill(left, Integer.MIN_VALUE);
        
        int maxArea = -1;
        for (int i = 0; i < nRows; i++) {
            int curLeft = 0, curRight = nCols;
            for (int j = 0, jr = nCols - 1; j < nCols; j++, jr--) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                    height[j] += 1;
                } else {
                    left[j] = Integer.MIN_VALUE;
                    curLeft = j + 1;
                    height[j] = 0;
                }
                
                if (matrix[i][jr] == '1') {
                    right[jr] = Math.min(right[jr], curRight);
                } else {
                    right[jr] = Integer.MAX_VALUE;
                    curRight = jr;
                }
            }
            
            for (int j = 0; j < nCols; j++) {
                int area = height[j] * (right[j] - left[j]);
                if (maxArea < area) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
}
