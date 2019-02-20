package leetcode.review;

public class SearchA2DMatrix_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int t = 0, b = matrix.length - 1;
        while (t <= b) {
            int m = (t + b) / 2;
            if (target < matrix[m][0]) {
                b = m - 1;
            } else if (target > matrix[m][0]) {
                t = m + 1;
            } else {
                return true;
            }
        }
        if (b < 0) {
            return false;
        }
        
        int l = 0, r = matrix[0].length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (target < matrix[b][m]) {
                r = m - 1;
            } else if (target > matrix[b][m]) {
                l = m + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
