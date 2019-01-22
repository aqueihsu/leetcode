package leetcode.dfsbfs;

// Need to think about this more
public class CrackingTheSafe_753 {
    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        boolean[] visited = new boolean[10 * 10 * 10 * 10];
        
        dfs(builder, visited, 0, (int) Math.pow(10, n - 1), k);
        // Append first node
        for (int i = 0; i < n - 1; i++) {
            builder.append('0');
        }
        
        return builder.toString();
    }
    
    private void dfs(StringBuilder builder, boolean[] visited, int node, int base, int k) {
        for (int kk = 0; kk < k; kk++) {
            int nodeAndEdge = node * 10 + kk;
            if (!visited[nodeAndEdge]) {
                visited[nodeAndEdge] = true;
                builder.append(node);
                dfs(builder, visited, nodeAndEdge % base, base, k);
                builder.append(kk);
            }
        }
    }
}
