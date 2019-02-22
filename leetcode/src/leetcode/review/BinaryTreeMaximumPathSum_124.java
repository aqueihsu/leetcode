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
        if (root == null) {
            return 0;
        }
        maxPathSum = root.val;
        maxDepthSum(root);
        return maxPathSum;
    }
    
    private int maxDepthSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepthSum = maxDepthSum(node.left);
        int rightDepthSum = maxDepthSum(node.right);
        
        int subDepthSum = Math.max(leftDepthSum, rightDepthSum);
        int currDepthSum = subDepthSum > 0 ? subDepthSum + node.val : node.val;
        int thruPathSum = leftDepthSum + rightDepthSum + node.val;
        
        maxPathSum = Math.max(Math.max(currDepthSum, thruPathSum), maxPathSum);
        return currDepthSum;
    }
}
