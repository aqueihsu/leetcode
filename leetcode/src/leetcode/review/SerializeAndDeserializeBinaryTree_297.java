package leetcode.review;

import java.util.Deque;
import java.util.LinkedList;

public class SerializeAndDeserializeBinaryTree_297 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(root.val);
        
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            processNode(queue, builder, node.left);
            processNode(queue, builder, node.right);
        }
        return builder.toString();
    }
    
    private void processNode(Deque<TreeNode> queue, StringBuilder builder, TreeNode node) {
        if (node == null) {
            builder.append(",null");
        } else {
            builder.append(",");
            builder.append(node.val);
            queue.push(node);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        
        
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            i = processVal(queue, values, i, node);
        }
        return root;
    }
    
    private int processVal(Deque<TreeNode> queue, String[] values, int i, TreeNode node) {
        if (!values[i].equals("null")) {
            node.left = new TreeNode(Integer.valueOf(values[i]));
            queue.push(node.left);
        }
        i++;
        if (!values[i].equals("null")) {
            node.right = new TreeNode(Integer.valueOf(values[i]));
            queue.push(node.right);
        }
        return ++i;
    }
}