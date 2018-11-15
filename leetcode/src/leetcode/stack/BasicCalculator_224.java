package leetcode.stack;

import java.util.Stack;

public class BasicCalculator_224 {
    public int calculate(String s) {
        Stack<Integer> results = new Stack<>();
        Stack<Character> ops = new Stack<>();
        
        int result = 0;
        char op = '+';
        
        int i = 0;
        int num = 0;
        
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '*' || c == '/') {
                if (op == '+' || op == '-') {
                    results.push(result);
                    ops.push(op);
                    result = num;
                } else {
                    result = evaluate(op, result, num);
                }
                num = 0;
                op = c;
            } else if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                results.push(result);
                ops.push(op);
                result = 0;
                op = '+';
            } else if (c == ')') {
                result = evaluate(op, result, num);
                if (op == '*' || op == '/') {
                    result = evaluate(ops.pop(), results.pop(), result);
                }
                num = result;
                result = results.pop();
                op = ops.pop();
            }
            if (i == s.length() - 1 || c == '+' || c == '-') {
                result = evaluate(op, result, num);
                if (op == '*' || op == '/') {
                    result = evaluate(ops.pop(), results.pop(), result);
                }
                num = 0;
                op = c;
            }
            i++;
        }
        return result;
    }
    private int evaluate(char op, int a, int b) {
        switch (op) {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            return a / b;
        }
        return 0;
    }
}
