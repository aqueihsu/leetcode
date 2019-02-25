package leetcode.review;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeVerticalOrderTraversal_314 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        
        
        Deque<TreeNode> queue = new LinkedList<>();
        Deque<Integer> indices = new LinkedList<>();
        
        queue.push(root);
        indices.push(0);
        
        List<List<Integer>> leftResults = new ArrayList<>();
        List<List<Integer>> rightResults = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            Deque<TreeNode> nextQueue = new LinkedList<>();
            Deque<Integer> nextIndices = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.pop();
                int index = indices.pop();
                
                if (index >= 0) {
                    // Right
                    if (rightResults.size() <= index) {
                        rightResults.add(new ArrayList<>());
                    }
                    rightResults.get(index).add(node.val);
                } else {
                    // Left
                    int leftIndex = -index - 1;
                    if (leftResults.size() <= leftIndex) {
                        leftResults.add(new ArrayList<>());
                    }
                    leftResults.get(leftIndex).add(node.val);
                }
                if (node.left != null) {
                    nextQueue.add(node.left);
                    nextIndices.add(index - 1);
                }
                if (node.right != null) {
                    nextQueue.add(node.right);
                    nextIndices.add(index + 1);
                }
            }
            queue = nextQueue;
            indices = nextIndices;
        }
        
        Collections.reverse(leftResults);
        results.addAll(leftResults);
        results.addAll(rightResults);
        return results;
    }
}
