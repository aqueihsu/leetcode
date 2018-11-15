package leetcode.dp;

public class DecodeWaysII_639 {
    static final long BASE = 1000000007;
    
    public int numDecodings(String s) {
        if (s.charAt(0) == '0' || !isValid(s.charAt(0))) {
            return 0;
        }
        
        long[] numDecodings = new long[s.length() + 1];
        numDecodings[0] = 1;
        numDecodings[1] = s.charAt(0) == '*' ? 9 : 1;
        
        for (int i = 1; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if (!isValid(currChar)) {
                return 0;
            }
            
            if (currChar != '0') {
                long numPermutations = currChar == '*' ? 9 : 1;
                numDecodings[i + 1] = numDecodings[i] * numPermutations % BASE;
            }
            
            char prevChar = s.charAt(i - 1);
            if (prevChar == '1') {
                long numPermutations = currChar == '*' ? 9 : 1;
                numDecodings[i + 1] = (numDecodings[i + 1] + numDecodings[i - 1] * numPermutations) % BASE;
            } else if (prevChar == '2') {
                long numPermutations = currChar == '*' ? 6 : (currChar <= '6' ? 1 : 0);;
                numDecodings[i + 1] = (numDecodings[i + 1] + numDecodings[i - 1] * numPermutations) % BASE;
            } else if (prevChar == '*') {
                long numPermutations = currChar == '*' ? 15 : (currChar <= '6' ? 2 : 1);
                numDecodings[i + 1] = (numDecodings[i + 1] + numDecodings[i - 1] * numPermutations) % BASE;
            }
        }
        
        return (int) numDecodings[s.length()];
    }
    
    private boolean isValid(char c) {
        return c >= '0' && c <= '9' || c == '*';
    }
}
