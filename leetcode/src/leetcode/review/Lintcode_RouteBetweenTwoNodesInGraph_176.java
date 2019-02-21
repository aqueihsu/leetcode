package leetcode.review;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Lintcode_RouteBetweenTwoNodesInGraph_176 {
    private class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }
    
    public boolean hasRoute(ArrayList<DirectedGraphNode> graph, DirectedGraphNode s, DirectedGraphNode t) {
        if (s.label == t.label) {
            return true;
        }
        
        Deque<DirectedGraphNode> queue = new LinkedList<>();
        queue.add(s);
        
        Set<Integer> visisted = new HashSet<>();
        visisted.add(s.label);
        
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (!visisted.contains(neighbor.label)) {
                    queue.add(neighbor);
                    visisted.add(neighbor.label);
                    if (neighbor.label == t.label) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
