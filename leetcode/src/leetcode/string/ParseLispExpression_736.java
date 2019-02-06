package leetcode.string;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ParseLispExpression_736 {
    private Deque<Map<String, Integer>> scopes;
    public int evaluate(String expression) {
        scopes = new LinkedList<>();
        return eval(expression, new int[] {0});
    }
    
    private int eval(String string, int[] pos) {
        // System.out.println(string.substring(pos[0]));
        if (pos[0] >= string.length()) {
            return 0;
        }
        if (string.charAt(pos[0]) == '(') {
            pos[0]++;
        }
        int result = 0;
        if (string.startsWith("add", pos[0])) {
            pos[0] += 4;
            result = eval(string, pos);
            pos[0]++; // Spacing
            result += eval(string, pos);
        } else if (string.startsWith("mult", pos[0])) {
            pos[0] += 5;
            result = eval(string, pos);
            pos[0]++; // Spacing
            result *= eval(string, pos);
        } else if (string.startsWith("let", pos[0])) {
            pos[0] += 4;
            
            Map<String, Integer> scope = new HashMap<>();
            scopes.addFirst(scope);
            
            while (true) {
                if (string.charAt(pos[0]) == '(') {
                    // Returning expression
                    result = eval(string, pos);
                    break;
                }
                int endIndex = indexOfNextExpression(string, pos[0]);
                String variable = string.substring(pos[0], endIndex);
                
                if (endIndex == string.length() || string.charAt(endIndex) == ')') {
                    // Returning expression
                    result = eval(string, pos);
                    break;
                } else {
                    pos[0] = endIndex + 1;
                    int value = eval(string, pos);
                    scope.put(variable, value);
                    pos[0]++; // Spacing
                }
            }
            scopes.pollFirst();
        } else {
            // Variable or number
            int endIndex = indexOfNextExpression(string, pos[0]);
            String substring = string.substring(pos[0], endIndex);
            pos[0] = endIndex;
            
            if (isNumeric(substring)) {
                result = Integer.valueOf(substring);
            } else {
                for (Map<String, Integer> scope : scopes) {
                    if (scope.containsKey(substring)) {
                        result = scope.get(substring);
                        break;
                    }
                }
            }
        }
        if (pos[0] < string.length() && string.charAt(pos[0]) == ')') {
            pos[0]++;
        }
        return result;
    }
    
    private boolean isNumeric(String string) {
        int i = 0;
        if (string.charAt(i) == '-') {
            i++;
        }
        for (; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c > '9' || c < '0') {
                return false;
            }
        }
        return true;
    }
    
    private int indexOfNextExpression(String string, int offset) {
        for (int i = offset; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == ' ' || c == '(' || c == ')') {
                return i;
            }
        }
        return string.length();
    }
}
