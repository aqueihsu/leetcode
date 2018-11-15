package leetcode.string;

public class AddStrings_415 {
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        int carryOver = 0;
        while (carryOver > 0 || i >= 0 || j >= 0) {
            int sum = (i >= 0 ? num1.charAt(i--) - '0' : 0) + carryOver;
            sum += j >= 0 ? num2.charAt(j--) - '0' : 0;
            carryOver = sum / 10;
            builder.append(sum % 10);
        }
        return builder.reverse().toString();
    }
}
