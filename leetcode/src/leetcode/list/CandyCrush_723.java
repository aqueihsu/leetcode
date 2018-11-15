package leetcode.list;

public class CandyCrush_723 {
    public int[][] candyCrush(int[][] board) {
        int m = board.length, n = board[0].length;
        while (true) {
            boolean isStable = true;
            for (int i = 0; i < m; i++) {
                int counter = 0;
                for (int j = 1; j < n; j++) {
                    counter = (board[i][j] != 0 && board[i][j] == Math.abs(board[i][j - 1])) ? counter + 1 : 0;
                    if (counter >= 2) {
                        if (counter == 2) {
                            board[i][j] = board[i][j - 1] = board[i][j - 2] = -board[i][j];
                        } else {
                            board[i][j] = board[i][j - 1];
                        }
                        isStable = false;
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                int counter = 0;
                for (int i = 1; i < m; i++) {
                    counter = (board[i][j] != 0 && Math.abs(board[i][j]) == Math.abs(board[i - 1][j])) ? counter + 1 : 0;
                    if (counter >= 2) {
                        if (counter == 2) {
                            board[i][j] = board[i - 1][j] = board[i - 2][j] = -Math.abs(board[i][j]);
                        } else {
                            board[i][j] = board[i - 1][j];
                        }
                        isStable = false;
                    }
                }
            }
            if (isStable) {
                break;
            }
            int[] validJs = new int[n];
            for (int j = 0; j < n; j++) {
                validJs[j] = m - 1;
            }
            for (int i = m - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    int boardVal = board[i][j];
                    board[i][j] = 0;
                    if (boardVal > 0) {
                        board[validJs[j]--][j] = boardVal;
                    }
                }
            }
        }
        
        return board;
    }
}
