package leetcode.dp;

// https://leetcode.com/problems/out-of-boundary-paths/description/
public class OutOfBoundaryPaths_576 {
    private static final long BASE = 1000000007;
    public int findPaths(int m, int n, int N, int i, int j) {
        return dp2(m, n, N, i, j);
    }
    
    // Only keep the previous N
    private int dp2(int m, int n, int N, int i, int j) {
        long[][] ans1 = new long[50][50];
        
        for (int Ni = 1; Ni <= N; Ni++) {
            long[][] ans2 = new long[50][50];
            for (int mi = 0; mi < m; mi++) {
                for (int ni = 0; ni < n; ni++) {
                    ans2[mi][ni] += (mi == 0) ?      1 : ans1[mi - 1][ni];
                    ans2[mi][ni] += (mi == m - 1) ?  1 : ans1[mi + 1][ni];
                    ans2[mi][ni] += (ni == 0) ?      1 : ans1[mi][ni - 1];
                    ans2[mi][ni] += (ni == n - 1) ?  1 : ans1[mi][ni + 1];
                    ans2[mi][ni] %= BASE;
                }
            }
            ans1 = ans2;
        }
        return (int) ans1[i][j];
    }
    
    private int dp1(int m, int n, int N, int i, int j) {
        long[][][] ans = new long[51][50][50];
        
        // N = 0, ans[0][all][all] = 0 which is the default value
        
        // N > 0
        for (int Ni = 1; Ni <= N; Ni++) {
            for (int mi = 0; mi < m; mi++) {
                for (int ni = 0; ni < n; ni++) {
                    ans[Ni][mi][ni] += (mi == 0) ?      1 : ans[Ni - 1][mi - 1][ni];
                    ans[Ni][mi][ni] += (mi == m - 1) ?  1 : ans[Ni - 1][mi + 1][ni];
                    ans[Ni][mi][ni] += (ni == 0) ?      1 : ans[Ni - 1][mi][ni - 1];
                    ans[Ni][mi][ni] += (ni == n - 1) ?  1 : ans[Ni - 1][mi][ni + 1];
                    ans[Ni][mi][ni] %= BASE;
                }
            }
        }
        return (int) ans[N][i][j];
    }
}
