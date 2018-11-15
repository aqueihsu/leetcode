package kth;

public class KthSmallestNumberinMultiplicationTable_668 {
    public int findKthNumber(int m, int n, int k) {
        int min = 1, max = m * n;
        
        while (min < max) {
            int mid = (min + max) / 2;
            
            int maxElem = -1;
            int count = 0;
            int i = 1, j = n;
            int product;
            while (i <= m && j >= 1) {
                if ((product = i * j) > mid) {
                    j--;
                } else {
                    count += j;
                    if (maxElem < product) {
                        maxElem = product;
                    }
                    i++;
                }
            }
            
            if (count < k) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }
}
