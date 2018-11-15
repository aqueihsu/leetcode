package leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SerializeAndDeserializeBST_449 {
    public class TreeNode {
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
        
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        
        StringBuilder builder = new StringBuilder();
        boolean isLeafLayer = false;
        
        while (!queue.isEmpty() && !isLeafLayer) {
            List<TreeNode> nextQueue = new ArrayList<>();
            isLeafLayer = true;
            
            for (TreeNode node : queue) {
                if (node != root) {
                    builder.append(',');
                }
                if (node == null) {
                    builder.append('*');
                } else {
                    builder.append(node.val);
                    nextQueue.add(node.left);
                    nextQueue.add(node.right);
                    
                    isLeafLayer &= (node.left == null && node.right == null);
                }
            }
            queue = nextQueue;
        }
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] strNodes = data.split(",");
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.valueOf(strNodes[0]));
        queue.add(root);
        
        int i = 1;
        while (i < strNodes.length) {
            Queue<TreeNode> nextQueue = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (!strNodes[i].equals("*")) {
                    node.left = new TreeNode(Integer.valueOf(strNodes[i]));
                    nextQueue.add(node.left);
                }
                i++;
                if (!strNodes[i].equals("*")) {
                    node.right = new TreeNode(Integer.valueOf(strNodes[i]));
                    nextQueue.add(node.right);
                }
                i++;
            }
            queue = nextQueue;
        }
        return root;
    }
}
