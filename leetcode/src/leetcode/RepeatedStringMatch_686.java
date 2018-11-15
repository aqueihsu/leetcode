package leetcode;

public class RepeatedStringMatch_686 {
    public int repeatedStringMatch(String A, String B) {
        int[] lps = buildLps(B, A.length());
        for (int i = 0, j = 0; i < A.length();) {
            while (j < B.length() && B.charAt(j) == A.charAt((i + j) % A.length())) j++;
            if (j == B.length()) {
                return (int) Math.ceil((double)(i + j) / A.length());
            }
            
            if (j > lps.length) {
                return -1;
            }
            
            if (j > 0) {
                i += Math.max(1, j - lps[j - 1]);
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return -1;
    }
    
    private int[] buildLps(String str, int n) {
        int[] lps = new int[Math.min(str.length(), n)];
        for (int i = 1, len = 0; i < lps.length;) {
            if (str.charAt(i) == str.charAt(len)) {
                lps[i++] = ++len;
            } else {
                if (len == 0) {
                    lps[i++] = 0;
                } else {
                    len = lps[len - 1];
                }
            }
        }
        return lps;
    }
    
    public int repeatedStringMatch1(String A, String B) {
        for (int i = 0, j = 0; i < A.length(); i++, j = 0) {
            while (j < B.length() && B.charAt(j) == A.charAt((i + j) % A.length())) j++;
            if (j == B.length()) {
                return (int) Math.ceil((i + j) / A.length());
            }
        }
        return -1;
    }
}
