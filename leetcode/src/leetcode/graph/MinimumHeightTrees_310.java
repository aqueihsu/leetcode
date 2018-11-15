package leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumHeightTrees_310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<Set<Integer>> adjacencies = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjacencies.add(new HashSet<>());
        } 
        for (int[] edge : edges) {
            adjacencies.get(edge[0]).add(edge[1]);
            adjacencies.get(edge[1]).add(edge[0]);
        }
        
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adjacencies.get(i).size() == 1) {
                leaves.add(i);
            }
        }    
        
        while (n > 2) {
            n -= leaves.size();
            
            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                int adjNode = adjacencies.get(leaf).iterator().next();  // The one of only neighbor of the leaf
                adjacencies.get(adjNode).remove(leaf);
                
                if (adjacencies.get(adjNode).size() == 1) {
                    newLeaves.add(adjNode);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}
