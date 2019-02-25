package leetcode.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePaths_257 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        helper(paths, new Stack<String>(), root);
        return paths;
    }
    
    private void helper(List<String> paths, Stack<String> path, TreeNode node) {
        if (node == null) {
            return;
        }
        
        path.push(String.valueOf(node.val));
        if (node.left == null && node.right == null) {
            paths.add(String.join("->", path));
        } else {
            helper(paths, path, node.left);
            helper(paths, path, node.right);
        }
        path.pop();
    }
}
