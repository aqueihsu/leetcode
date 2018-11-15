package leetcode.tree;

public class SameTree_100 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) {
            return q == null;
        }
        if (q == null) {
            return p == null;
        }
        return isSameTree(p.left, q.left) && p.val == q.val && isSameTree(p.right, q.right);
    }
}
