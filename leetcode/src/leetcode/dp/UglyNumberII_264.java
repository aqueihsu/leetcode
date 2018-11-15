package leetcode.dp;

public class UglyNumberII_264 {
    public int nthUglyNumber(int n) {
        int id2 = 0, id3 = 0, id5 = 0;
        int val2 = 2, val3 = 3, val5 = 5;
        int uglyNum[] = new int[n];
        uglyNum[0] = 1;
        for (int i = 1; i < n; i++) {
            uglyNum[i] = Math.min(val2, Math.min(val3, val5));
            if (uglyNum[i] == val2) {
                val2 = 2 * uglyNum[++id2];
            }
            if (uglyNum[i] == val3) {
                val3 = 3 * uglyNum[++id3];
            }
            if (uglyNum[i] == val5) {
                val5 = 5 * uglyNum[++id5];
            }
        }
        return uglyNum[n-1];
    }
    
    public int nthSuperUglyNumber(int n, int[] primes) {
        int id[] = new int[primes.length];
        int uglyNum[] = new int[n];
        uglyNum[0] = 1;
        for (int i = 1; i < n; i++) {
            int temp;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                if (min > (temp = uglyNum[id[j]] * primes[j])) {
                    min = temp;
                }
            }
            for (int j = 0; j < primes.length; j++) {
                if (min == uglyNum[id[j]] * primes[j]) {
                    id[j]++;
                }
            }
            uglyNum[i] = min;
        }
        return uglyNum[n-1];
    }
    
    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        while (num % 2 == 0) {
            num >>= 1;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }
}
