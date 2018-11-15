package leetcode.list;

public class ConvertSortedListToBinarySearchTree_109 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    // Sol1: slow-fast pointer
    public TreeNode sortedListToBST_slowFast(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST_slowFast(head);
        root.right = sortedListToBST_slowFast(slow.next);
        return root;
    }
    // Sol2: inorder traversal
    private ListNode node;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        int len = 0;
        ListNode runner = head;
        while ((runner = runner.next) != null) {
            len++;
        }
        node = head;
        return inorderTraverse(0, len);
    }
    private TreeNode inorderTraverse(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        
        TreeNode left = inorderTraverse(start, mid - 1);
        TreeNode root = new TreeNode(node.val);
        root.left = left;
        
        node = node.next;
        root.right = inorderTraverse(mid + 1, end);
        
        return root;
    }
}
