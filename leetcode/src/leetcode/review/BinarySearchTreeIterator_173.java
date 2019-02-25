package leetcode.review;

import java.util.Stack;

public class BinarySearchTreeIterator_173 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    class BSTIterator {
        private Stack<TreeNode> stack = new Stack<>();
        
        public BSTIterator(TreeNode root) {
            helper(root);
        }
    
        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    
        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            if (node.right != null) {
                helper(node.right);
            }
            return node.val;
        }
        
        private void helper(TreeNode node) {
            if (node == null) {
                return;
            }
            stack.push(node);
            while (node.left != null) {
                stack.push(node = node.left);
            }
        }
    }
}
