package leetcode;

public class ProductOfArrayExceptSelf_238 {
    public int[] productExceptSelf(int[] nums) {
        int[] products = new int[nums.length];
        
        products[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            products[i] = products[i + 1] * nums[i + 1];
        }
        
        int currProduct = 1;
        for (int i = 1; i < nums.length; i++) {
            currProduct *= nums[i - 1];
            products[i] *= currProduct;
        }
        return products;
    }
}
