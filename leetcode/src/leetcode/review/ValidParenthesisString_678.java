package leetcode.review;

public class ValidParenthesisString_678 {
    public boolean checkValidString(String s) {
        int nWildcards = 0;
        int nOpens = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                nOpens++;
            } else if (c == ')') {
                nOpens--;
            } else {
                nWildcards++;
            }
            if (nOpens < 0 && -nOpens > nWildcards) {
                return false;
            }
        }
        
        nWildcards = nOpens = 0;
        for (int i = s.length() - 1; i >= 0 ; i--) {
            char c = s.charAt(i);
            if (c == ')') {
                nOpens++;
            } else if (c == '(') {
                nOpens--;
            } else {
                nWildcards++;
            }
            if (nOpens < 0 && -nOpens > nWildcards) {
                return false;
            }
        }
        return true;
    }
}
