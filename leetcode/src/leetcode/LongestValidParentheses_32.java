package leetcode;

import java.util.Stack;

public class LongestValidParentheses_32 {
    public int longestValidParentheses(String s) {
        return leftRightCounters(s);
    }
    
    private int leftRightCounters(String s) {
        int left = 0, right = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, left << 1);
            } else if (right > left) {
                left = right = 0;
            }
        }
        
        left = 0; right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLength = Math.max(maxLength, right << 1);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxLength;
    }
    
    private int dpSolution(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int maxLen = 0;
        
        int[] dp = new int[s.length()];
        dp[0] = 0;
        dp[1] = (s.charAt(0) == '(' && s.charAt(1) == ')') ? maxLen = 2 : 0;
        for (int i = 2; i < s.length(); i++) {
            char prevChar = s.charAt(i - 1);
            char currChar = s.charAt(i);
            if (currChar == ')') {
                if (prevChar == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (prevChar == ')' && i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            if (maxLen < dp[i]) {
                maxLen = dp[i];
            }
        }
        return maxLen;
    }
    
    private int stackSolution(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // mark the beginning and save a isEmpty check
        
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                // RIGHT_PARENTHESE
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    int length = i - stack.peek();
                    if (maxLength < length) {
                        maxLength = length;
                    }
                }
            }
        }
        return maxLength;
    }
}
