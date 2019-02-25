package leetcode.review;

import java.util.Stack;

public class LowestCommonAncestorOfABinaryTree_236 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    private Stack<TreeNode> stack = new Stack<>();
    private boolean foundOne = false;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        boolean found = helper(root, p, q);
        return found ? stack.pop() : null;
    }
    
    private boolean helper(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }
        if (!foundOne) {
            stack.push(node);
            foundOne = node == p || node == q;
        } else if (node == p || node == q) {
            return true;
        }
        boolean foundTwo = helper(node.left, p, q) || helper(node.right, p, q);
        if (stack.peek() == node && !foundTwo) {
            stack.pop();
        }
        return foundTwo;
    }
}
