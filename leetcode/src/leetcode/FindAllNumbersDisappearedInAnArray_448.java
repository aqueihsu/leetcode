package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray_448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return new ArrayList<Integer>(0);
        }
        
        for (int num : nums) {
            int absNum = Math.abs(num);
            int pos = absNum - 1;
            if (nums[pos] > 0) {
                nums[pos] = -nums[pos];
            }
        }
        
        List<Integer> disappeared = new ArrayList<>(2);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                disappeared.add(i + 1);
            }
        }
        return disappeared;
    }
}
