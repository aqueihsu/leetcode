package leetcode.dp;

public class MaximalSquare_221 {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        
        int numRows = matrix.length, numCols = matrix[0].length;
        int[] numOnes1 = new int[numCols + 1];
        
        int maxCtsOnes = 0;
        for (int r = 0; r < numRows; r++) {
            int[] numOnes2 = new int[numCols + 1];
            
            for (int c = 0; c < numCols; c++) {
                numOnes2[c + 1] = matrix[r][c] == '0' ? 0 : (Math.min(numOnes2[c], Math.min(numOnes1[c], numOnes1[c + 1])) + 1);
                if (numOnes2[c + 1] > maxCtsOnes) {
                    maxCtsOnes = numOnes2[c + 1];
                }
            }
            numOnes1 = numOnes2;
        }
        
        return maxCtsOnes * maxCtsOnes;
    }
}
