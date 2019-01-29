package leetcode.tree;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal_106 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }
    
    // Count the number of elements in the subtree!
    private TreeNode helper(int[] inorder, int inStart, int inEnd, /* exclusive */
            int[] postorder, int postStart, int postEnd) {
        if (inStart >= inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd - 1]);
        int inRoot = inStart;
        for (;inRoot < inEnd && inorder[inRoot] != root.val; inRoot++);
        
        int nLeft = inRoot - inStart;
        root.left = helper(inorder, inStart, inRoot, postorder, postStart, postStart + nLeft);
        root.right = helper(inorder, inRoot + 1, inEnd, postorder, postStart + nLeft, postEnd - 1);
        return root;
    }
}
