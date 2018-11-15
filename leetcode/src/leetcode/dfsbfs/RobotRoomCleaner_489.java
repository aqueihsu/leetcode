package leetcode.dfsbfs;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RobotRoomCleaner_489 {
    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        public boolean move();

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft();
        public void turnRight();

        // Clean the current cell.
        public void clean();
    }
    
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up, right, down, left, counterclock-wise
    
    // Avoid collision!
    private class Node {
        int i;
        int j;
        
        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
        
        @Override
        public int hashCode() {
            int res = 17;
            res = res * 31 + i;
            res = res * 31 + j;
            return res;
        }
        
        @Override
        public boolean equals(Object obj) {
            Node other = (Node) obj;
            return i == other.i && j == other.j;
        }
    }
    
    public void cleanRoom(Robot robot) {
        dfs(robot, 0, new Node(0, 0), new HashSet<Node>());
    }
    
    private void dfs(Robot robot, int idxDir, Node node, Set<Node> cleaned) {
        robot.clean();
        cleaned.add(node);
        
        for (int k = 0; k < DIRECTIONS.length; k++) {
            int idxDirNext = (idxDir + k) % DIRECTIONS.length;
            
            Node nextNode = new Node(node.i + DIRECTIONS[idxDirNext][0],
                    node.j + DIRECTIONS[idxDirNext][1]);
            
            // Ordering is very important! You don't want to move unless necessary
            if (!cleaned.contains(nextNode) && robot.move()) {
                dfs(robot, idxDirNext, nextNode, cleaned);
                
                robot.turnLeft(); // Go back
                robot.turnLeft();
                robot.move();
                robot.turnLeft(); // Get ready for next position
            } else {
                robot.turnRight(); // Get ready for next position if this move isn't valid
            }
        }
    }
}
