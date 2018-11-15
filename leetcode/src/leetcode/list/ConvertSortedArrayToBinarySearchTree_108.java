package leetcode.list;

public class ConvertSortedArrayToBinarySearchTree_108 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return helper(0, nums.length - 1, nums);
    }
    private TreeNode helper(int start, int end, int[] nums) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(start, mid - 1, nums);
        root.right = helper(mid + 1, end, nums);
        return root;
    }
}
