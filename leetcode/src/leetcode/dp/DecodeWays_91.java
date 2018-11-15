package leetcode.dp;

import javax.print.attribute.IntegerSyntax;

// Lots of edge cases, e.g. 10, 0 etc.
public class DecodeWays_91 {
    public int numDecodings(String s) {
        if (!isNumber(s.charAt(0)) || s.charAt(0) == '0') {
            return 0;
        }
        int[] nDecodings = new int[s.length() + 1];
        nDecodings[0] = 0;
        nDecodings[1] = 1;
        
        for (int i = 1; i < s.length(); i++) {
            char currentDigit = s.charAt(i);
            if (!isNumber(currentDigit)) {
                return 0;
            }
            
            // Current digit as a number
            if (currentDigit > '0') {
                nDecodings[i + 1] = nDecodings[i];
            }
            
            // Current digit and previous digit combined as a number
            char previousDigit = s.charAt(i - 1);
            if (previousDigit == '1' || previousDigit == '2') {
                int combinedDigits = currentDigit - '0' + 10 * (previousDigit - '0');
                if (combinedDigits <= 26 && combinedDigits >= 1) {
                    nDecodings[i + 1] += nDecodings[i - 1];
                }
            }
        }
        return nDecodings[s.length()];
    }
    
    private boolean isNumber(char c) {
        return c <= '9' && c >= '0';
    }
}
