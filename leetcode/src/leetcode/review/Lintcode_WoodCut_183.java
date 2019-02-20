package leetcode.review;

public class Lintcode_WoodCut_183 {
    public int woodCut(int[] lengths, int k) {
        long l = 1;
        long r = l;
        for (long length : lengths) {
            if (r < length) {
                r = length;
            }
        }
        
        boolean hasEligibleCut = false;
        while (l <= r) {
            long length = (l + r) / 2;
            if (count(lengths, length) >= k) {
                hasEligibleCut = true;
                l = length + 1;
            } else {
                // count(lengths, length) < k
                r = length - 1;
            }
            
        }
        return hasEligibleCut ? (int) r : 0;
    }
    
    private long count(int[] lengths, long targetLen) {
        long c = 0;
        for (long length : lengths) {
            c += length / targetLen;
        }
        return c;
    }
}
