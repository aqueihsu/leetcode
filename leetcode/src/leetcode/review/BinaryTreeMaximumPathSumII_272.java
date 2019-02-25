package leetcode.review;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

public class BinaryTreeMaximumPathSumII_272 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<Double> vals = new PriorityQueue<>(
                (d1, d2) -> {
                        double result = Math.abs(d2 - target) - Math.abs(d1 - target);
                        if (result > 0) {
                            return 1;
                        } else if (result == 0) {
                            return 0;
                        } else {
                            return -1;
                        }
                });
        helper(vals, root, target, k, 0);

        List<Integer> results = new ArrayList<>(k);
        for (Double val : vals) {
            results.add(val.intValue());
        }
        return results;
    }
    
    private void helper(PriorityQueue<Double> vals, TreeNode node, double target, int k, int depth) {
        if (node == null) {
            return;
        }
        
        helper(vals, node.left, target, k, depth);
        vals.add((double) node.val);
        while (vals.size() > k) {
            vals.poll();
        }
        helper(vals, node.right, target, k, depth);
    }
}
