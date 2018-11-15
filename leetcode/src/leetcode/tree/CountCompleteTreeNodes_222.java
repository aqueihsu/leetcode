package leetcode.tree;

public class CountCompleteTreeNodes_222 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int h = 0;
        TreeNode left = root, right = root;
        while (right != null) {
            right = right.right;
            left = left.left;
            h++;
        }
        if (left == null) { // full tree
            return (1 << h) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
