package leetcode.dp;

public class NumberOfMusicPlaylists_920 {
    private static final int MOD = 1000000007;
    public int numMusicPlaylists(int N, int L, int K) {
        long[][] dp = new long[L + 1][N + 1]; // Note! Boundaries
        dp[0][0] = 1; // Note!
        
        for (int i = 1; i <= L; i++) {   // L: number of songs she wants to listen to
            for (int j = 1; j <= N; j++) {   // N: number of different songs
                dp[i][j] = (dp[i - 1][j - 1] * (N - (j - 1)) % MOD
                        + dp[i - 1][j] * Math.max(j - K, 0) % MOD) % MOD;
            }
        }
        return (int) dp[L][N];
    }
}
