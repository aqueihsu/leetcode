package leetcode.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PalindromePartitioning_131 {
    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        if (s.length() > 0) {
            helper(s, 0, new Stack<String>(), results);
        }
        return results;
    }
    private void helper(String s, int start, Stack<String> result, List<List<String>> results) {
        if (start == s.length()) {
            results.add(new ArrayList<>(result));
        }
        int end = start;
        while (end < s.length()) {
            if (isPalindrome(s, start, end)) {
                result.push(s.substring(start, end + 1));
                helper(s, end + 1, result, results);
                result.pop();
            }
            end++;
        }
    }
    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}
