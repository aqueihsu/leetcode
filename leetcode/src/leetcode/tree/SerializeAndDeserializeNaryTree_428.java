package leetcode.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SerializeAndDeserializeNaryTree_428 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        dfsSerialize(builder, root);
        return builder.toString();
    }
    
    private void dfsSerialize(StringBuilder builder, Node node) {
        builder.append(node.val);
        
        List<Node> children = node.children;
        if (children == null || children.isEmpty()) {
            return;
        }
        
        builder.append('[');
        Iterator<Node> itor = node.children.iterator();
        dfsSerialize(builder, itor.next());
        while (itor.hasNext()) {
            builder.append(' ');
            dfsSerialize(builder, itor.next());
        }
        builder.append(']');
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        Node dummy = new Node(0, new ArrayList<>());
        dfsDeserialize(data, 0, dummy);
        return dummy.children.get(0);
    }
    
    private int dfsDeserialize(String data, int i, Node parent) {
        int j = i;
        while (j < data.length() && Character.isDigit(data.charAt(j))) {
            j++;
        }
        
        int val = Integer.valueOf(data.substring(i, j));
        i = j;
        
        Node node = new Node(val, new ArrayList<>());
        parent.children.add(node);
        
        boolean hasChildren = i < data.length() && data.charAt(i) == '[';
        if (hasChildren) {
            i++; // skip '['
            while (i < data.length() && data.charAt(i) != ']') {
                if (data.charAt(i) == ' ') {
                    i++;
                } else {
                    i = dfsDeserialize(data, i, node);
                }
            }
            i++; // skip ']'
        }
        return i;
    }
}
