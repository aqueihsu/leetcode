package leetcode;

public class PlusOne_66 {
    public int[] plusOne(int[] digits) {
        int carryOver = 1;
        int i = digits.length;
        while (--i >= 0 && carryOver > 0) {
            int sum = digits[i] + carryOver;
            digits[i] = sum % 10;
            carryOver = sum / 10;
        }
        if (carryOver > 0) {
            int[] ans = new int[digits.length + 1];
            ans[0] = carryOver;
            System.arraycopy(digits, 0, ans, 1, digits.length);
            return ans;
        }
        return digits;
    }
}
