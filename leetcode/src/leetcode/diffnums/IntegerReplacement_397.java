package leetcode.diffnums;

public class IntegerReplacement_397 {
    public int integerReplacement(int n) {
        long nl = n;
        long mask2 = 0b11;
        long mask1 = 1;
        int count = 0;
        while (nl != 1) {
            if ((nl & mask1) == 0) {
                // Even number
                nl >>= 1;
            } else if ((nl & mask2) == mask2 && nl >= 0b111) {
                // Odd number, ending with multiple consecutive 1s
                nl += 1;
            } else {
                // Odd number, ending with one 1
                nl -= 1;
            }
            count++;
        }
        return count;
    }
}
