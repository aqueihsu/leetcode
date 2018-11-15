package leetcode.dp;

import org.junit.Test;

public class LongestIncreasingPathInAMatrix_329 {
    @Test
    public void test() {
        int[][] matrix = new int[][] {{0,1,2,3,4,5,6,7,8,9},{19,18,17,16,15,14,13,12,11,10},{20,21,22,23,24,25,26,27,28,29},{39,38,37,36,35,34,33,32,31,30},{40,41,42,43,44,45,46,47,48,49},{59,58,57,56,55,54,53,52,51,50},{60,61,62,63,64,65,66,67,68,69},{79,78,77,76,75,74,73,72,71,70},{80,81,82,83,84,85,86,87,88,89},{99,98,97,96,95,94,93,92,91,90},{100,101,102,103,104,105,106,107,108,109},{119,118,117,116,115,114,113,112,111,110},{120,121,122,123,124,125,126,127,128,129},{139,138,137,136,135,134,133,132,131,130},{0,0,0,0,0,0,0,0,0,0}};
        System.out.println(longestIncreasingPath(matrix));
    }
    
    // Tip: remember the answer from DFS!
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int m = matrix.length, n = matrix[0].length;
        int[][] maxLengths = new int[m][n];
        int maxLength = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLength = Math.max(maxLength, findMaxLength(maxLengths, matrix, i, j));
            }
        }
        return maxLength;
    }
    
    private int findMaxLength(int[][] maxLengths, int[][] matrix, int i, int j) {
        if (maxLengths[i][j] > 0) {
            return maxLengths[i][j];
        }
        int maxLength = 1;
        if (i > 0 && matrix[i][j] < matrix[i - 1][j]) {
            maxLength = Math.max(maxLength, findMaxLength(maxLengths, matrix, i - 1, j) + 1);
        }
        if (i < matrix.length - 1 && matrix[i][j] < matrix[i + 1][j]) {
            maxLength = Math.max(maxLength, findMaxLength(maxLengths, matrix, i + 1, j) + 1);
        }
        if (j > 0 && matrix[i][j] < matrix[i][j - 1]) {
            maxLength = Math.max(maxLength, findMaxLength(maxLengths, matrix, i, j - 1) + 1);
        }
        if (j < matrix[0].length - 1 && matrix[i][j] < matrix[i][j + 1]) {
            maxLength = Math.max(maxLength, findMaxLength(maxLengths, matrix, i, j + 1) + 1);
        }
        return maxLengths[i][j] = maxLength;
    }
}
