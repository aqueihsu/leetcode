package leetcode.dp;

import java.util.HashMap;

// https://leetcode.com/articles/super-egg-drop/
public class SuperEggDrop_887 {
    public int superEggDrop(int K, int N) {
        return dp_bottomUp(K, N);
        // return dp(K, N, ans);
    }
    
    private int dp_bottomUp(int K, int N) {
        // k = 1
        int[] ans1 = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            ans1[i] = i;
        }
        
        // k = 2 to K
        for (int k = 2; k <= K; k++) {
            int[] ans2 = new int[N + 1];
            
            // ans2[0] = 0, ans2[1] = 1 (always)
            int x = 1;
            for (int n = 1; n <= N; n++) {
                while (x < n && Math.max(ans1[x - 1], ans2[n - x]) > Math.max(ans1[x], ans2[n - x - 1])) {
                    x++;
                }
                ans2[n] = 1 + Math.max(ans1[x - 1], ans2[n - x]);
            }
            
            ans1 = ans2;
        }
        return ans1[N];
    }
    
    HashMap<Integer, Integer> ans = new HashMap<>();    // size: K*N
    private int dp(int K, int N, HashMap<Integer, Integer> ans) {
        if (N == 0) {
            return 0;
        }
        if (K == 1) {
            return N;
        }
        
        final Integer lookupKey = key(K, N);
        if (!ans.containsKey(lookupKey)) {
            int l = 1, h = N;
            while (l + 1 < h) {
                int m = (l + h) / 2;
                
                int t1 = dp(K - 1, m - 1, ans);
                int t2 = dp(K, N - m, ans);
                
                if (t1 > t2) {
                    h = m;
                } else if (t1 < t2) {
                    l = m;
                } else {
                    l = h = m;
                }
            }
            ans.put(lookupKey, 1 + Math.min(Math.max(dp(K - 1, l - 1, ans), dp(K, N - l, ans)),
                            Math.max(dp(K - 1, h - 1, ans), dp(K, N - h, ans))));
        }
        return ans.get(lookupKey);
    }
    
    private Integer key(int K, int N) {
        return K + N * 100;
    }
}
