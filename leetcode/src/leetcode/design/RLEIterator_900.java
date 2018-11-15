package leetcode.design;

public class RLEIterator_900 {
    class RLEIterator {
        private final int[] array;
        private int idx;
        
        public RLEIterator(int[] A) {
            array = A;
            idx = 0;
        }
        
        public int next(int n) {
            while (idx < array.length && n > 0) {
                if (array[idx] < n) {
                    n -= array[idx];
                    array[idx] = 0;
                    idx += 2;
                } else {
                    // array[idx] >= n)
                    array[idx] -= n;
                    return array[idx + 1];
                }
            }
            return -1;
        }
    }
}
