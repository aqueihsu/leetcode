package leetcode.dp;

public class PerfectSquares_279 {
    public int numSquares(int n) {
        if (n == 0) {
            return 0;
        }
        int squareSums[] = new int[n + 1];
        squareSums[0] = 0;
        for (int i = 1; i <= n; i++) {
            squareSums[i] = i;
            for (int j = 1, jSquare; (jSquare = j * j) <= i; j++) {
                squareSums[i] = Math.min(squareSums[i], squareSums[i - jSquare] + 1);
            }
        }
        return squareSums[n];
    }
}
