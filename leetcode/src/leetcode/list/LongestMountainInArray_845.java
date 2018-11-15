package leetcode.list;

public class LongestMountainInArray_845 {
    public int longestMountain(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        int up = 0, down = 0;
        int maxLen = 0;
        for (int i = 1; i < A.length; i++) {
            if (down > 0 && A[i - 1] < A[i] || A[i - 1] == A[i]) {
                up = down = 0;
            }
            if (A[i - 1] > A[i]) {
                down++;
            }
            if (A[i - 1] < A[i]) {
                up++;
            }
            if (up > 0 && down > 0) {
                maxLen = Math.max(maxLen, up + down + 1);
            }
        }
        return maxLen;
    }
    
    public int longestMountain1(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        boolean summited = false, hiking = false;
        int maxLen = 0;
        
        int i = 0, j = 1;
        while (j < A.length) {
            while (j < A.length && A[j - 1] >= A[j]) {
                j++;
            }
            i = j - 1;
            
            while (j < A.length && A[j - 1] < A[j]) {
                hiking = true;
                j++;
            }
            while (j < A.length && A[j - 1] > A[j]) {
                summited = true;
                j++;
            }
            if (hiking && summited) {
                maxLen = Math.max(maxLen, j - i);
            }
            hiking = summited = false;
        }
        return maxLen;
    }
}
