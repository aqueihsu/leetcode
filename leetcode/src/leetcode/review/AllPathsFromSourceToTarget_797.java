package leetcode.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AllPathsFromSourceToTarget_797 {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(results, new Stack<>(), graph, 0);
        return results;
    }
    
    private void dfs(List<List<Integer>> results, Stack<Integer> stack, int[][] graph, int node) {
        if (node == graph.length - 1) {
            stack.push(node);
            results.add(new ArrayList<>(stack));
            stack.pop();
            return;
        }
        if (graph[node].length > 0) {
            stack.push(node);
            for (int neighbor : graph[node]) {
                dfs(results, stack, graph, neighbor);
            }
            stack.pop();
        }
    }
}
