package validateBST;

import java.util.Stack;

public class Iterative_CheckPrevNode {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode curNode = root;
        long prevElem = Long.MIN_VALUE;
        while (!nodes.isEmpty() || curNode != null) {
            if (curNode != null) {
                nodes.push(curNode);
                curNode = curNode.left;
            } else {
                TreeNode node = nodes.pop();
                if (prevElem >= node.val) {
                    return false;
                }
                prevElem = node.val;
                curNode = node.right;
            }
        }
        return true;
    }
}
