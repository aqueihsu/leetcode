package leetcode.review;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII_437 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    // [1], 0
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        return helper(preSumMap, 0, root, sum);
    }
    
    private int helper(Map<Integer, Integer> preSumMap, int preSum, TreeNode node, int target) {
        if (node == null) {
            return 0;
        }
        int curSum = preSum + node.val;
        int count = preSumMap.getOrDefault(curSum - target, 0);
        
        preSumMap.put(curSum, preSumMap.getOrDefault(curSum, 0) + 1);
        count += helper(preSumMap, curSum, node.left, target);
        count += helper(preSumMap, curSum, node.right, target);
        preSumMap.put(curSum, preSumMap.get(curSum) - 1);
        return count;
    }
    
    public int pathSumDFS(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumFrom(root, sum) + pathSumDFS(root.left, sum) + pathSumDFS(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode root, int remaining) {
        if (root == null) {
            return 0;
        }
        return (remaining == root.val ? 1 : 0)
                + pathSumFrom(root.left, remaining - root.val)
                + pathSumFrom(root.right, remaining - root.val);
    }
}
