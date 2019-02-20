package leetcode.review;

public class KthSmallestElementInASortedMatrix_378 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        long l = matrix[0][0];
        long r = matrix[n - 1][n - 1];
        
        // [[3]]
        while (l <= r) {
            long m = (l + r) / 2;
            
            if (count(matrix, m) < k) {
                l = m + 1;
            } else {
                // count(matrix, m) >= k
                r = m - 1;
            }
        }
        return (int) l;
    }
    
    private long count(int[][] matrix, long num) {
        int n = matrix.length;
        int i = 0;
        int j = n - 1;
        
        int count = 0;
        while (i < n && j >= 0) {
            if (matrix[i][j] > num) {
                j--;
            } else {
                count += j + 1;
                i++;
            }
        }
        return count;
    }
}
