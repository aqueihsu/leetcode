package leetcode.review;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal_102 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            Deque<TreeNode> nextQueue = new LinkedList<>();
            List<Integer> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                result.add(node.val);
                if (node.left != null) {
                    nextQueue.addLast(node.left);
                }
                if (node.right != null) {
                    nextQueue.addLast(node.right);
                }
            }
            queue = nextQueue;
            results.add(result);
        }
        return results;
    }
}
