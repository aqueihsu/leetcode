package leetcode.dp;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram_84 {
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length <= 0) {
            return 0;
        }
        
        int n = heights.length;
        int maxArea = -1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int h = heights[stack.pop()];
                int r = i;
                int l = stack.isEmpty() ? 0 : (stack.peek() + 1);
                int area = (r - l) * h;
                if (maxArea < area) {
                    maxArea = area;
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int h = heights[stack.pop()];
            int r = n;
            int l = stack.isEmpty() ? 0 : (stack.peek() + 1);
            int area = (r - l) * h;
            if (maxArea < area) {
                maxArea = area;
            }
        }
        return maxArea;
    }
    
    public int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length <= 0) {
            return 0;
        }
        
        int n = heights.length;
        int[] lefts = new int[n], rights = new int[n];
        lefts[0] = 0;
        rights[n - 1] = n;
        
        int curLeft = 0, curRight = n;
        for (int l = 1, r = n - 2; l < n; l++, r--) {
            if (heights[l] <= heights[l - 1]) {
                lefts[l] = curLeft;
            } else {
                lefts[l] = curLeft = l;
            }
            
            if (heights[r] <= heights[r + 1]) {
                rights[r] = curRight;
            } else {
                rights[r] = curRight = r + 1;
            }
        }
        
        int maxArea = -1;
        for (int i = 0; i < n; i++) {
            int j = lefts[i];
            while (j > 0 && heights[i] <= heights[j - 1]) {
                j = lefts[i] = lefts[j - 1];
            }
            int k = rights[i];
            while (k < n - 1 && heights[i] <= heights[k]) {
                k = rights[i] = rights[k];
            }
            
            int area = heights[i] * (rights[i] - lefts[i]);
            if (maxArea < area) {
                maxArea = area;
            }
        }
        return maxArea;
    }
}
