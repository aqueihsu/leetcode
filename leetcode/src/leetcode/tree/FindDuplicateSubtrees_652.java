package leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees_652 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    private int t = 1;  // Can't be the same with the null case
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> duplicates = new ArrayList<>();
        if (root != null) {
            Map<String, Integer> treeIds = new HashMap<>();
            Map<Integer, Integer> counters = new HashMap<>();
            dfs(treeIds, counters, duplicates, root);
        }
        return duplicates;
    }
    
    private int dfs(Map<String, Integer> treeIds, Map<Integer, Integer> counters, List<TreeNode> duplicates, TreeNode node) {
        int left = node.left == null ? 0 : dfs(treeIds, counters, duplicates, node.left);
        int right = node.right == null ? 0 : dfs(treeIds, counters, duplicates, node.right);
        
        // Generating ids from ids removes the overhead of generating strings
        StringBuilder builder = new StringBuilder();
        builder.append(node.val).append(',').append(left).append(',').append(right);
        
        String key = builder.toString();
        int uid = treeIds.computeIfAbsent(key, keyy -> t++);
        if (!counters.containsKey(uid)) {
            counters.put(uid, 1);
        } else {
            counters.put(uid, counters.get(uid) + 1);
            if (counters.get(uid) == 2) {
                duplicates.add(node);
            }
        }
        return uid;
    }
}
