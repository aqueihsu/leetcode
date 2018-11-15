package kth;

public class KthSmallestPrimeFraction {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int n = A.length;
        double min = 0d, max = 1d;
        while (Double.compare(min, max) < 0) {
            double mid = (min + max) / 2d;
            
            int i = 0, j = 1;
            int q = 0, p = 1;
            int count = 0;
            while (i < n && j < n) {
                if (Double.compare(A[i], mid * A[j]) >= 0) {
                    j++;
                } else {
                    if (Double.compare(p * A[i], q * A[j]) > 0) {
                        q = A[i];
                        p = A[j];
                    }
                    
                    count += (n - j);
                    i++;
                }
            }
            
            if (count < K) {
                min = mid;
            } else if (count > K) {
                max = mid;
            } else {
                return new int[] {q, p};
            }
        }
        return null;
    }
}
