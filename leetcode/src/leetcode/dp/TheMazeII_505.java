package leetcode.dp;

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
        visited = new boolean[m][n];
        distance = new int[m][n];
        
        visited[start[0]][start[1]] = true;
        dfs(maze, start, destination);
        
        return distance[destination[0]][destination[1]] == 0 ? -1 : distance[destination[0]][destination[1]];
    }
    
    private void dfs(int[][] maze, int[] pos, int[] dest) {
        for (Direction direction : Direction.values()) {
            int[] nextPos = move(maze, pos, direction);
            if (!visited[nextPos[0]][nextPos[1]]) {
                int nextDistance = distance[pos[0]][pos[1]] + Math.abs(nextPos[0] - pos[0]) + Math.abs(nextPos[1] - pos[1]);
                if (distance[nextPos[0]][nextPos[1]] > 0
                        && distance[nextPos[0]][nextPos[1]] < nextDistance) {
                    continue;
                }
                distance[nextPos[0]][nextPos[1]] = nextDistance;
                if (nextPos[0] != dest[0] || nextPos[1] != dest[1]) {
                    visited[nextPos[0]][nextPos[1]] = true;
                    dfs(maze, nextPos, dest);
                    visited[nextPos[0]][nextPos[1]] = false;
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
