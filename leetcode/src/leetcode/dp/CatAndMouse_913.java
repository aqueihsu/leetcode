package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CatAndMouse_913 {
    private int[][] graph;
    private int[][][] colors;
    private int[][][] degrees;
    private static int DRAW = 0, MOUSE = 1, CAT = 2;
    private static int HOLE = 0;
    
    private class Node {
        int cPos;
        int mPos;
        int turn;
        public Node(int cPos, int mPos, int turn) {
            this.cPos = cPos;
            this.mPos = mPos;
            this.turn = turn;
        }
    }
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        if (n == 0) {
            return 0;
        }
        this.graph = graph;
        
        colors = new int[n][n][3];
        degrees = new int[n][n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // no-op: colors[i][j][MOUSE] = colors[i][j][CAT] = DRAW;
                degrees[i][j][MOUSE] = graph[j].length;
                degrees[i][j][CAT] = graph[i].length;
                
                // Don't forget to remove HOLE from cat node!
                if (Arrays.stream(graph[i]).anyMatch(pos -> pos == HOLE)) {
                    degrees[i][j][CAT]--;
                }
            }
        }
        
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                colors[i][i][MOUSE] = colors[i][i][CAT] = CAT;
                queue.add(new Node(i, i, MOUSE));
                queue.add(new Node(i, i, CAT));
            }
            colors[i][0][MOUSE] = colors[i][0][CAT] = MOUSE;
            queue.add(new Node(i, 0, MOUSE));
            queue.add(new Node(i, 0, CAT));
        }
        
        // Fill out parents bottom-up
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int color = colors[node.cPos][node.mPos][node.turn];
            
            List<Node> parents = fetchParents(node);
            for (Node parent : parents) {
                int parentColor = colors[parent.cPos][parent.mPos][parent.turn];
                if (parentColor == DRAW) {
                    // E.g. parent is MOUSE turn, and child is colored as MOUSE (mouse going to MOUSE)
                    if (parent.turn == color) {
                        colors[parent.cPos][parent.mPos][parent.turn] = color;
                        queue.add(parent);
                    } else {
                        /*
                        if (color == DRAW) {
                            // This should never happens
                            // Starting nodes are either MOUSE or CAT, only losing child node will remove the node degree
                            System.out.println("DRAW");
                        }
                        */
                        // Losing child node for this parent node
                        int degree = --degrees[parent.cPos][parent.mPos][parent.turn];
                        if (degree == 0) {
                            colors[parent.cPos][parent.mPos][parent.turn] = 3 - parent.turn; // Losing: Opposite player wins
                            queue.add(parent);
                        }
                    }
                }
            }
        }
        return colors[2][1][MOUSE];
    }
    
    private List<Node> fetchParents(Node node) {
        List<Node> parents;
        if (node.turn == MOUSE) {
            parents = new ArrayList<>(graph[node.cPos].length);
            for (int cPos : graph[node.cPos]) {
                if (cPos != 0) {
                    parents.add(new Node(cPos, node.mPos, CAT));
                }
            }
        } else {
            // CAT
            parents = new ArrayList<>(graph[node.mPos].length);
            for (int mPos : graph[node.mPos]) {
                parents.add(new Node(node.cPos, mPos, MOUSE));
            }
        }
        return parents;
    }
}
