package leetcode.list;

public class BSTToSortedDoublyLinkedList_426 {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
    private Node prev = new Node(0, null, null);
    private Node head = prev;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        inorderTraverse(root);
        head = head.right;
        head.left = prev;
        prev.right = head;
        return head;
    }
    private void inorderTraverse(Node node) {
        if (node == null) {
            return;
        }
        inorderTraverse(node.left);
        node.left = prev;
        prev.right = node;
        prev = node;
        inorderTraverse(node.right);
    }
}
