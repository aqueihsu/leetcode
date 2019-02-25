package leetcode.review;

public class ClosestBinarySearchTreeValue_270 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    private TreeNode prev = null;
    private TreeNode next = null;
    
    public int closestValue(TreeNode root, double target) {
        closestValues(root, (long) target + 1);
        
        if (prev == null) {
            return next.val;
        } else if (next == null) {
            return prev.val;
        } else {
            if (Math.abs((double) prev.val - target) < Math.abs((double) next.val - target)) {
                return prev.val;
            } else {
                return next.val;
            }
        }
    }
    
    
    public boolean closestValues(TreeNode root, long target) {
        if (root == null) {
            return false;
        }
        if (closestValues(root.left, target)) {
            return true;
        }
        if (root.val >= target) {
            next = root;
            return true;
        }
        prev = root;
        return closestValues(root.right, target);
    }
}
