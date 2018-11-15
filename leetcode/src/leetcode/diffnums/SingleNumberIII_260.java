package leetcode.diffnums;

public class SingleNumberIII_260 {
    public int[] singleNumber(int[] nums) {
        int xXORY = 0;
        for (int num : nums) {
            xXORY ^= num;
        }
        
        //  int mask = xXORY & ~(xXORY-1);  //Generate a number with the rightmost set bit of the xor
        int mask = 1;
        while ((mask & xXORY) == 0) {
            mask <<= 1;
        }
        
        int x = 0;
        int y = 0;
        for (int num : nums) {
            if ((mask & num) == 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[] {x, y};
    }
}
