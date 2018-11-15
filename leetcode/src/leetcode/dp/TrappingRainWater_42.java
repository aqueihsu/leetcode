package leetcode.dp;

import java.util.Stack;

public class TrappingRainWater_42 {
    public int trap(int[] height) {
        int trapped = 0;
        int left = 0, right = height.length - 1;
        int maxLeft = 0, maxRight = 0;
        while (left <= right) {
            if (height[left] < height[right]) {
                if (maxLeft < height[left]) {
                    maxLeft = height[left];
                } else {
                    trapped += maxLeft - height[left];
                }
                left++;
            } else {
                if (maxRight < height[right]) {
                    maxRight = height[right];
                } else {
                    trapped += maxRight - height[right];
                }
                right--;
            }
        }
        return trapped;
    }
    
    public int trap1(int[] height) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        
        int trapped = 0;
        int n = height.length;
        for (int i = 1; i < n; i++) {
            while(!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int prevIndex = stack.pop();
                if (!stack.isEmpty()) {
                    int prevPrevIndex = stack.peek();
                    int delta = (height[i] < height[prevPrevIndex] ? height[i] : height[prevPrevIndex]) - height[prevIndex];
                    trapped += delta * (i - prevPrevIndex - 1);
                }
            }
            stack.push(i);
        }
        return trapped;
    }
}
