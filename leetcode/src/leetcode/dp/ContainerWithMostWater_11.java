package leetcode.dp;

public class ContainerWithMostWater_11 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxWater = 0;
        while (left < right) {
            int water;
            if (height[left] < height[right]) {
                water = (right - left) * height[left];
                left++;
            } else {
                water = (right - left) * height[right];
                right--;
            }
            if (maxWater < water) {
                maxWater = water;
            }
        }
        
        return maxWater;
    }
}
