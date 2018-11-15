package leetcode.dfsbfs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ClosestLeafInABinaryTree_742 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int findClosestLeaf(TreeNode root, int k) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        Set<Integer> leaves = new HashSet<>();
        adj.put(root.val, new HashSet<>());
        dfs(adj, leaves, root);
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(k);
        while (!queue.isEmpty()) {
            Queue<Integer> nextQueue = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                int node = queue.poll();
                if (leaves.contains(node)) {
                    return node;
                }
                if (adj.containsKey(node)) {
                    nextQueue.addAll(adj.get(node));
                    adj.remove(node);
                }
            }
            queue = nextQueue;
        }
        return root.val;
    }
    private void dfs(Map<Integer, Set<Integer>> adj, Set<Integer> leaves, TreeNode node) {
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
            return;
        }
        int val = node.val;
        if (node.left != null) {
            int leftVal = node.left.val;
            adj.get(val).add(leftVal);
            
            if (!adj.containsKey(leftVal)) {
                adj.put(leftVal, new HashSet<>());
            }
            adj.get(leftVal).add(val);
            dfs(adj, leaves, node.left);
        }
        if (node.right != null) {
            int rightVal = node.right.val;
            adj.get(val).add(rightVal);
            
            if (!adj.containsKey(rightVal)) {
                adj.put(rightVal, new HashSet<>());
            }
            adj.get(rightVal).add(val);
            dfs(adj, leaves, node.right);
        }
    }
}
