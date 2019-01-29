package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class RecoverBinarySearchTree_99 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    private List<TreeNode> nodes = new ArrayList<>(2);
    private TreeNode prevNode;
    
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root);
        
        int tmp = nodes.get(0).val;
        nodes.get(0).val = nodes.get(1).val;
        nodes.get(1).val = tmp;
    }
    
    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        
        inorder(node.left);
        if (prevNode != null && prevNode.val > node.val) {
            if (nodes.size() == 0) {
                // Take the previous node
                nodes.add(prevNode);
                nodes.add(node);
            } else {
                // Take the second node
                nodes.set(1, node);
                return; // Found both nodes
            }
        }
        prevNode = node;
        inorder(node.right);
    }
}
