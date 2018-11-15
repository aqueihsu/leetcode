package kth;

public class KthSmallestElement_BinarySearch {
    public int kthSmallest(int[][] matrix, int k) {
        int n;
        if (matrix == null || (n = matrix.length) == 0 || k > n*n) {
            return 0;
        }
        
        int min = matrix[0][0], max = matrix[n-1][n-1];
        if (n == 1) {
            return min;
        }
        if (k == n*n) {
            return max;
        }
        
        while (min <= max) {
            int mid = (min + max) / 2;
            int count = numSmallerElementOf(matrix, mid);
            if (count < k) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return min;
    }
    
    private int numSmallerElementOf(int[][] matrix, int target) {
        int n = matrix.length;
        int i = 0;
        int j = n - 1;
        int count = 0;
        while (i < n && j >= 0) {
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
                count += (j + 1);
            }
        }
        return count;
    }
    
    // Tell me how this handles the case where n = 1, or n = 2, or k = n*n?
}
