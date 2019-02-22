package leetcode.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathSumII_113 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        helper(root, sum, new Stack<Integer>(), results);
        return results;
    }
    
    private void helper(TreeNode root, int sum, Stack<Integer> result, List<List<Integer>> results) {
        if (root == null) {
            return;
        }
        
        sum -= root.val;
        result.push(root.val);
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                results.add(new ArrayList<>(result));
            }
        } else {
            helper(root.left, sum, result, results);
            helper(root.right, sum, result, results);
        }
        result.pop();
    }
}
