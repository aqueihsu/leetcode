package validateBST;

public class Recursion_CheckBounds {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long low, long high) {
        if (node == null) {
            return true;
        }
        
        if (node.val <= low || node.val >= high) {
            return false;
        }
        
        return isValidBST(node.left, low, node.val)
                && isValidBST(node.right, node.val, high);
    }
}
