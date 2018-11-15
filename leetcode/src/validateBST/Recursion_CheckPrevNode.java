package validateBST;

public class Recursion_CheckPrevNode {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, new long[] {Long.MIN_VALUE});
    }

    private boolean isValidBST(TreeNode node, long[] previousElem) {
        if (node == null) {
            return true;
        }
        
        boolean isValid = isValidBST(node.left, previousElem) && previousElem[0] < node.val;
        previousElem[0] = node.val;
        
        return isValid && isValidBST(node.right, previousElem);
    }
}
