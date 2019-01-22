package kth;

import java.util.PriorityQueue;

import org.junit.Test;

public class KthSmallestElement_PriorityQueue {
	private class Wrapper {
        int i;
        int j;
        
        public Wrapper(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        int n;
        if (matrix == null || (n = matrix.length) == 0 || k > n*n) {
            return 0;
        }
        if (n == 1) {
            return matrix[0][0];
        }
        if (k == n*n) {
            return matrix[n-1][n-1];
        }
        
        PriorityQueue<Wrapper> queue = new PriorityQueue<>(k, (w1, w2) -> (matrix[w1.i][w1.j] - matrix[w2.i][w2.j]));
        for (int i = 0; i < n; i++) {
            queue.add(new Wrapper(i, 0));
        }
        
        for (int i = 1; i < k; i++) {
            Wrapper wrapper = queue.poll();
            if (++wrapper.j < n) {
                queue.add(wrapper);
            }
        }
        return matrix[queue.peek().i][queue.peek().j];
    }
}
