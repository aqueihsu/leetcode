package leetcode.dp;

import java.util.ArrayDeque;
import java.util.Queue;

public class TheMazeII_505 {
    private enum Direction {
        LEFT, RIGHT, UP, DOWN;
    }
    
    int m, n;
    int[][] distance;
    boolean[][] visited;
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        distance = new int[m][n];
        visited = new boolean[m][n];
        
        bfs(maze, start, destination);
        
        int result = distance[destination[0]][destination[1]];
        return result == 0 ? -1 : result;
    }
    
    private void bfs(int[][] maze, int[] start, int[] dest) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (Direction direction : Direction.values()) {
                int[] nextPos = move(maze, pos, direction);
                if (!visited[nextPos[0]][nextPos[1]]) {
                    visited[nextPos[0]][nextPos[1]] = true;
                    
                    int nextDistance = distance[pos[0]][pos[1]] + Math.abs(nextPos[0] - pos[0]) + Math.abs(nextPos[1] - pos[1]);
                    distance[nextPos[0]][nextPos[1]] = distance[nextPos[0]][nextPos[1]] > 0 ?
                            Math.min(distance[nextPos[0]][nextPos[1]], nextDistance) : nextDistance;
                            
                    if (nextPos[0] != dest[0] || nextPos[1] != dest[1]) {
                        queue.add(nextPos);
                    }
                }
            }
        }
    }
    
    private int[] move(int[][] maze, int[] start, Direction direction) {
        int x = start[0], y = start[1];
        switch (direction) {
        case UP:
            while (--x >= 0 && maze[x][y] == 0);
            x++;
            break;
        case DOWN:
            while (++x < m && maze[x][y] == 0);
            x--;
            break;
        case LEFT:
            while (--y >= 0 && maze[x][y] == 0);
            y++;
            break;
        case RIGHT:
            while (++y < n && maze[x][y] == 0);
            y--;
            break;
        }
        return new int[] {x, y};
    }
}
