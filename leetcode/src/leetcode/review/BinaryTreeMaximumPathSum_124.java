package leetcode.review;

public class BinaryTreeMaximumPathSum_124 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    // [-2,null,-3], expected -2
    int maxPathSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        maxDepthSum(root);
        return maxPathSum;
    }
    
    private int maxDepthSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSum = maxDepthSum(node.left);
        int rightSum = maxDepthSum(node.right);
        
        int leftMidSum = leftSum + node.val;
        int rightMidSum = rightSum + node.val;
        int midSum = leftSum + rightSum + node.val;
        
        maxPathSum = Math.max(Math.max(rightMidSum, rightMidSum), Math.max(midSum, maxPathSum));
        return Math.max(leftSum, rightSum) + node.val;
    }
}
