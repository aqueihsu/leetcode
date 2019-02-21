package leetcode.review;

public class LongestUnivaluePath_687 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = new int[] {Integer.MIN_VALUE};
        findMax(max, root);
        return max[0];
    }
    
    private int findMax(int[] max, TreeNode node) {
        int len = 0, leftLen = 0, rightLen = 0;
        if (node.left != null) {
            leftLen = findMax(max, node.left);
            if (node.val == node.left.val) {
                leftLen++;
                len += leftLen;
            } else {
                leftLen = 0;
            }
        }
        if (node.right != null) {
            rightLen = findMax(max, node.right);
            if (node.val == node.right.val) {
                rightLen++;
                len += rightLen;
            } else {
                rightLen = 0;
            }
        }
        max[0] = Math.max(max[0], len);
        return Math.max(leftLen, rightLen);
    }
}
