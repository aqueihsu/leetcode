package leetcode.dp;

import java.util.Arrays;

public class KnightProbabilityInChessboard_688 {
    static final int[] MOVES_ROW = {1, 1, 2, 2, -1, -1, -2, -2};
    static final int[] MOVES_COL = {2, -2, 1, -1, -2, 2, 1, -1};
    static final int NUM_MOVES = MOVES_ROW.length;
    
    public double knightProbability(int N, int K, int r, int c) {
        double[][] ans1 = new double[N][N];
        for (double[] ans : ans1) {
            Arrays.fill(ans, 1d);
        }
        
        for (int kk = 1; kk <= K; kk++) {
            double[][] ans2 = new double[N][N];
            for (int rr = 0; rr < N; rr++) {
                for (int cc = 0; cc < N; cc++) {
                    for (int m = 0; m < NUM_MOVES; m++) {
                        int curR = rr + MOVES_ROW[m];
                        int curC = cc + MOVES_COL[m];
                        if (curR >= 0 && curR < N && curC >= 0 && curC < N) {
                            ans2[curR][curC] += ans1[rr][cc]/8d;
                        }
                    }
                }
            }
            ans1 = ans2;
        }
        return ans1[r][c];
    }
}
