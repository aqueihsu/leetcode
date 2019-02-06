package leetcode.review;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber_17 {
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits.length() > 0) {
            helper(digits, 0, new char[digits.length()], 0, results);
        }
        return results;
    }
    
    private void helper(String digits, int iDigit, char[] result, int iResult, List<String> results) {
        if (iDigit == digits.length()) {
            results.add(new String(result));
            return;
        }
        int digit = digits.charAt(iDigit);
        if (digit < '2' || digit > '9') {
            helper(digits, iDigit + 1, result, iResult, results);
        } else {
            int nChars = digit == '7' || digit == '9' ? 4 : 3;
            char start = (char) ('a' + (digit - '2') * 3 + (digit > '7' ? 1 : 0));
            
            for (int i = 0; i < nChars; i++) {
                result[iResult] = (char) start++;
                helper(digits, iDigit + 1, result, iResult + 1, results);
            }
        }
    }
}
