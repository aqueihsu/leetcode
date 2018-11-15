package leetcode.string;

public class AddBinary_67 {
    public String addBinary(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int carryOver = 0;
        int m = a.length(), n = b.length();
        int i = m - 1, j = n - 1;
        do {
            int numA = i >= 0 ? a.charAt(i--) - '0' : 0;
            int numB = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = numA + numB + carryOver;
            carryOver = sum / 2;
            builder.append(sum % 2);
        } while (carryOver != 0 || i >= 0 || j >= 0);
        return builder.reverse().toString();
    }
}
