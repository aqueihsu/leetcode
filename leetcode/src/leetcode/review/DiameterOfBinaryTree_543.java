package leetcode.review;

public class DiameterOfBinaryTree_543 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    int maxDiameter = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxDepth(root);
        return maxDiameter;
    }
    
    private int maxDepth(TreeNode root) {
        int leftLen = root.left != null ? (maxDepth(root.left) + 1) : 0;
        int rightLen = root.right != null ? (maxDepth(root.right) + 1) : 0;
        
        maxDiameter = Math.max(maxDiameter, leftLen + rightLen);
        return Math.max(leftLen, rightLen);
    }
}
