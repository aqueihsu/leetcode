package leetcode.tree;

public class RedundantConnectionII_685 {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] candidateA = null, candidateB = null;
        
        int n = edges.length;
        int[] parents = new int[n + 1];
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            if (parents[b] == 0) {
                parents[b] = a;
            } else {
                // Node b has two parents
                candidateA = new int[] {parents[b], b};
                candidateB = edge;
            }
        }
       
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
        
        for (int[] edge : edges) {
            if (candidateB != null && edge[0] == candidateB[0] && edge[1] == candidateB[1]) {
                // Dropping candidateB and check if it's a valid tree
                // This is so that in the case that both candidates work, candidateB should be returned
                continue;
            }
            
            int a = edge[0], b = edge[1];
            int pa = find(parents, a), pb = find(parents, b);
            if (pa == pb) {
                if (candidateB == null /* && candidateA == null */) {
                    // Circle
                    return edge;
                }
                // Dropping candidateB didn't fix the issue -> candidateA is the culprit
                return candidateA;
            } else {
                // Union
                parents[a] = pb;
            }
        }
        // Dropping candidateB fixes the issue -> candidateB is the culprit
        return candidateB;
    }
    private int find(int[] parents, int a) {
        if (a != parents[a]) {
            parents[a] = find(parents, parents[a]);
        }
        return parents[a];
    }
}
