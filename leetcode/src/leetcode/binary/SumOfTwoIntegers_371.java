package leetcode.binary;

import org.junit.Test;

public class SumOfTwoIntegers_371 {
    @Test
    public void testShift() {
        getSum(-1, 1);
    }
    
    public int getSum(int a, int b) {
        int ans = 0;
        int mask = 1;
        
        int sum = 0;
        int carryOver = 0;
        do {
            // Note: don't use "> 0" so that it handles both negative and positive numbers
            if (((a ^ b) & mask) != 0) {
                // 1 + 0 + carryOver
                sum = mask ^ carryOver;
                carryOver <<= 1;
            } else {
                // 1 + 1 + carryOver
                // 0 + 0 + carryOver
                sum = carryOver;
                carryOver = (a & b & mask) << 1;
            }
            ans |= sum;
            mask <<= 1;
        } while (mask != 0);
       
        return ans;
    }
}
