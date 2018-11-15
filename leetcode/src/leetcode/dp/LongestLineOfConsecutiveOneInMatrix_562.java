package leetcode.dp;

public class LongestLineOfConsecutiveOneInMatrix_562 {
    public int longestLine(int[][] M) {
        int maxCount = 0;
        if (M.length == 0 || M[0].length == 0) {
            return maxCount;
        }
        
        int m = M.length, n = M[0].length;
        int[] vCount = new int[n];
        int[] dCount = new int[n];
        int[] vdCount = new int[n];
        for (int i = 0; i < m; i++) {
            int hCount = 0;
            for (int j = 0, k = n - 1; j < n; j++, k--) {
                if (M[i][k] == 1) {
                    dCount[k] = k > 1 ? dCount[k - 1] + 1 : 1;
                    maxCount = Math.max(maxCount, dCount[k]);
                } else {
                    dCount[k] = 0;
                }
                if (M[i][j] == 1) {
                    hCount++;
                    vCount[j]++;
                    vdCount[j] = j < n - 1 ? vdCount[j + 1] + 1 : 1;
                    maxCount = Math.max(Math.max(Math.max(hCount, vCount[j]), vdCount[j]), maxCount);
                } else {
                    hCount = 0;
                    vCount[j] = 0;
                    vdCount[j] = 0;
                }
            }
        }
        return maxCount;
    }
}
