package leetcode.dp;

// Boundary cases!!!
public class RangeSumQuery2D_304 {
    private int[][] sums;
    private int m, n;
    
    public RangeSumQuery2D_304(int[][] matrix) {
        if (matrix == null || (m = matrix.length) <= 0 || (n = matrix[0].length) <= 0) {
            return;
        }
        sums = new int[m][n];
        sums[0][0] = matrix[0][0];
        for (int j = 1; j < n; j++) {
            sums[0][j] = sums[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < m; i++) {
            int curRowSum = 0;
            for (int j = 0; j < n; j++) {
                curRowSum += matrix[i][j];
                sums[i][j] = sums[i - 1][j] + curRowSum;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int bottomLeftSum = (col1 == 0) ? 0 : sums[row2][col1 - 1];
        int bottomRightSum = sums[row2][col2];
        int topRightSum = (row1 == 0) ? 0 : sums[row1 - 1][col2];
        int topLeftSum = (row1 == 0 || col1 == 0) ? 0 : sums[row1 - 1][col1 - 1];
        return bottomRightSum - bottomLeftSum - topRightSum + topLeftSum;
    }
}
