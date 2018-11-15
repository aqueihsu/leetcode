package leetcode.string;

public class BasicCalculatorII_227 {
    public int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        
        int result = 0;
        int i = 0, j = 0;
        char prevOp = '+';
        while (j < s.length()) {
            while (j < s.length() && (s.charAt(j) <= '9' && s.charAt(j) >= '0')) {
                j++;
            }
            
            char op = '+';
            int resultDivMult = Integer.valueOf(s.substring(i, j));
            while (j < s.length() && ((op = s.charAt(j)) == '*' || op == '/')) {
                i = ++j;
                while (j < s.length() && s.charAt(j) <= '9' && s.charAt(j) >= '0') {
                    j++;
                }
                int currNum = Integer.valueOf(s.substring(i, j));
                if (op == '*') {
                    resultDivMult *= currNum;
                } else {
                    resultDivMult /= currNum;
                }
            }
            
            if (prevOp == '+') {
                result += resultDivMult;
            } else if (prevOp == '-') {
                result -= resultDivMult;
            }
            prevOp = op;
            i = ++j;
        }
        return result;
    }
}
