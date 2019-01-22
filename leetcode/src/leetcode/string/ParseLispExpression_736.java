package leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ParseLispExpression_736 {
    public int evaluate(String expression) {
    }
    
    private boolean isNumeric(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
