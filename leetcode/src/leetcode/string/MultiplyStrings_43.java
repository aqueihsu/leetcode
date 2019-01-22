package leetcode.string;

public class MultiplyStrings_43 {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] vals = new int[m + n];
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int pos1 = i + j, pos2 = pos1 + 1;
                int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + vals[pos2];
                vals[pos2] = sum % 10;
                vals[pos1] += sum / 10;
            }
        }
        
        StringBuilder builder = new StringBuilder();
        for (int val : vals) {
            if (!(builder.length() == 0 && val == 0)) {
                builder.append(val);
            }
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }
    public String multiply1(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        
        String curValue = null;
        for (int j = num2.length() - 1; j >= 0; j--) {
            String value = multiplyString(num1, num2.charAt(j), num2.length() - j - 1);
            curValue = addStrings(curValue, value);
        }
        return curValue;
    }
    
    private String multiplyString(String num, char c, int padding) {
        if (c == '0') {
            return "0";
        }
        int carryOver = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = num.length() - 1; i >= 0; i--) {
            int sum = carryOver + (num.charAt(i) - '0') * (c - '0');
            carryOver = sum / 10;
            builder.append((char) (sum % 10 + '0'));
        }
        while (carryOver > 0) {
            builder.append((char)(carryOver % 10 + '0'));
            carryOver /= 10;
        }
        builder.reverse();
        while (--padding >= 0) {
            builder.append('0');
        }
        return builder.toString();
    }
    
    private String addStrings(String num1, String num2) {
        if (num1 == null) {
            return num2;
        }
        if (num2 == null) {
            return num1;
        }
        if (num1.length() < num2.length()) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        
        int i = num1.length() - 1;
        int carryOver = 0;
        StringBuilder builder = new StringBuilder();
        for (int j = num2.length() - 1; j >= 0; i--, j--) {
            int sum = carryOver + num1.charAt(i) + num2.charAt(j) - ('0' << 1);
            carryOver = sum / 10;
            builder.append((char) (sum % 10 + '0'));
        }
        while (i >= 0) {
            int sum = carryOver + num1.charAt(i--) - '0';
            carryOver = sum / 10;
            builder.append((char) (sum % 10 + '0'));
        }
        if (carryOver > 0) {
            builder.append('1');
        }
        return builder.reverse().toString();
    }
}
