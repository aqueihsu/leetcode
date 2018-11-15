package backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber_17 {
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return results;
        }
        helper(results, new char[digits.length()], 0, digits, 0);
        return results;
    }
    
    private void helper(List<String> results, char[] result, int iChar, String digits, int iDigit) {
        if (iDigit == digits.length()) {
            results.add(new String(result));
            return;
        }
        
        int nChars = (digits.charAt(iDigit) == '9' || digits.charAt(iDigit) == '7') ? 4 : 3;
        int startingChar = 'a' + (digits.charAt(iDigit) - '2') * 3 + (digits.charAt(iDigit) > '7' ? 1 : 0);
        for (int i = 0; i < nChars; i++) {
            result[iChar] = (char) (startingChar + i);
            helper(results, result, iChar + 1, digits, iDigit + 1);
        }
    }
}
