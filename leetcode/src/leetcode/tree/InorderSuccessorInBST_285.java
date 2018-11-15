package leetcode.tree;

public class InorderSuccessorInBST_285 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    };
    
    // Find the next value bigger than target
    private TreeNode inorderTraverse1(TreeNode node, TreeNode target) {
        if (node == null) {
            return null;
        }
        if (node.val <= target.val) {
            return inorderTraverse1(node.right, target);
        }
        // node.val > target.val
        TreeNode left = inorderTraverse1(node.left, target);
        return left != null ? left : node;
    }
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            return inorderTraverse(p.right);
        }
        TreeNode[] successor = new TreeNode[1];
        inorderTraverse(successor, root, p);
        return successor[0];
    }
    
    private TreeNode inorderTraverse(TreeNode node) {
        if (node == null || node.left == null) {
            return node;
        }
        return inorderTraverse(node.left);
    }
    
    private boolean inorderTraverse(TreeNode[] successor, TreeNode node, TreeNode target) {
        if (node == null) {
            return false;
        }
        
        if (node.val < target.val) {
            return inorderTraverse(successor, node.right, target);
        }
        if (node.val > target.val) {
            boolean found = false;
            if ((found = inorderTraverse(successor, node.left, target)) && successor[0] == null) {
                successor[0] = node;
            }
            return found;
        }
        return true;
    }
}
