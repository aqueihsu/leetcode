package validateBST;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Iterative_CheckBounds {
    class TreeNodeWrapper {
        TreeNode n;
        long low;
        long high;
        
        public TreeNodeWrapper(TreeNode n, long low, long high) {
            this.n = n;
            this.low = low;
            this.high = high;
        }
    }
    
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Stack<TreeNodeWrapper> nodes = new Stack<>();
        nodes.add(new TreeNodeWrapper(root, Long.MIN_VALUE, Long.MAX_VALUE));
        while (!nodes.isEmpty()) {
            TreeNodeWrapper node = nodes.pop();
            if (node.n.val <= node.low || node.n.val >= node.high) {
                return false;
            }
            if (node.n.left != null) {
                nodes.push(new TreeNodeWrapper(node.n.left, node.low, node.n.val));
            }
            if (node.n.right != null) {
                nodes.push(new TreeNodeWrapper(node.n.right, node.n.val, node.high));
            }
        }
        return true;
    }
}
