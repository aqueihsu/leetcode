package leetcode.dp;

public class RotateFunction_396 {
    public int maxRotateFunction(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int sum = 0;
        int rotateSums[] = new int [A.length];
        for (int i = 0; i < A.length; i++) {
            rotateSums[0] += i * A[i];
            sum += A[i];
        }
        
        int maxRotateSum = rotateSums[0];
        for (int i = 1; i < A.length; i++) {
            rotateSums[i] = rotateSums[i-1] + sum - A[A.length - i] * A.length;
            if (maxRotateSum < rotateSums[i]) {
                maxRotateSum = rotateSums[i];
            }
        }
        return maxRotateSum;
    }
}
