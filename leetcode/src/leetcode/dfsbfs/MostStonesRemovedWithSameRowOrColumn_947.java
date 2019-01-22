package leetcode.dfsbfs;

import java.util.HashSet;
import java.util.Set;

public class MostStonesRemovedWithSameRowOrColumn_947 {
    public int removeStones(int[][] stones) {
        DSU dsu = new DSU(20000);
        
        // Put x and y coordinates in the same set
        for (int[] stone : stones) {
            dsu.union(stone[0], 10000 + stone[1]);
        }
        
        // Find the number of components
        Set<Integer> parents = new HashSet<>();
        for (int[] stone : stones) {
            parents.add(dsu.find(stone[0]));
        }
        return stones.length - parents.size();
    }
    
    class DSU {
        private int[] parents;
        public DSU(int N) {
            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
        }
        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }
        public void union(int x, int y) {
            parents[find(x)] = find(y);
        }
    }
    
    // Trick: build connected graph by index of stones, not the positions of the stones
    public int removeStonesDFS(int[][] stones) {
        final int n = stones.length;
        boolean[][] adj = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adj[i][j] = adj[j][i] = stones[i][0] == stones[j][0] ||
                        stones[i][1] == stones[j][1];
            }
        }
        
        boolean[] visited = new boolean[n];
        int nRemovals = 0;
        for (int p = 0; p < n; p++) {
            if (!visited[p]) {
                nRemovals += dfs(adj, visited, p) - 1;
            }
        }
        return nRemovals;
    }
    
    private int dfs(boolean[][] adj, boolean[] visited, int p) {
        visited[p] = true;
        
        int count = 1;
        for (int q = 0; q < adj[p].length; q++) {
            if (adj[p][q] && !visited[q]) {
                count += dfs(adj, visited, q);
            }
        }
        return count;
    }
}
