package leetcode.graph;

public class IsGraphBipartite_785 {
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return false;
        }
        int[] colors = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0 && !dfs(graph, i, 1, colors)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean dfs(int[][] graph, int i, int color, int[] colors) {
        if (graph[i] == null || graph[i].length == 0) {
            return true;
        }
        if (colors[i] != 0) {
            // Visited
            return colors[i] == color;
        }
        colors[i] = color;
        for (int neighbor : graph[i]) {
            if (!dfs(graph, neighbor, -color, colors)) {
                return false;
            }
        }
        return true;
    }
}
