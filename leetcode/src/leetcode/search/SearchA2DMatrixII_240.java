package leetcode.search;

public class SearchA2DMatrixII_240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0; // starting from the bottom left corner
        while (i >= 0 && j < n) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
    
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        return helper(matrix, target, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }
    
    private boolean helper(int[][] matrix, int target, int row1, int col1, int row2, int col2) {
        if (row1 > row2 || col1 > col2) {
            return false;
        }
        int rowMid = (row1 + row2) / 2;
        int pos = binarySearch(matrix[rowMid], target, col1, col2);
        if (pos >= col1 && pos <= col2 && matrix[rowMid][pos] == target) {
            return true;
        } else {
            return helper(matrix, target, row1, pos, rowMid - 1, col2)
                    || helper(matrix, target, rowMid + 1, col1, row2, pos - 1);
        }
    }
    
    private int binarySearch(int[] nums, int target, int l, int r) {
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] < target) {
                l = m + 1;
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return l;
    }
}
