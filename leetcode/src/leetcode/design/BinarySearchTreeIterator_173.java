package leetcode.design;

import java.util.Stack;

public class BinarySearchTreeIterator_173 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    };
    
    public class BSTIterator {
        private Stack<TreeNode> stack = new Stack<>();
        
        public BSTIterator(TreeNode root) {
            pushNode(root);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            pushNode(node.right);
            return node.val;
        }
        
        private void pushNode(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    };
}
