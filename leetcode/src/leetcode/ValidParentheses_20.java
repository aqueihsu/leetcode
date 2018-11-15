package leetcode;

import java.util.Stack;

public class ValidParentheses_20 {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char prevC = stack.pop();
                if ((c == '}' && prevC != '{')
                        || (c == ']' && prevC != '[')
                        || (c == ')' && prevC != '(')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
