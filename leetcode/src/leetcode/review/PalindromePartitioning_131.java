package leetcode.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PalindromePartitioning_131 {
    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        if (s.length() > 0) {
            helper(s, new Stack<Integer>(), results);
        }
        return results;
    }
    private void helper(String s, Stack<Integer> result, List<List<String>> results) {
        if (result.size() == s.length()) {
            results.add(new ArrayList<>(result));
        }
    }
}
