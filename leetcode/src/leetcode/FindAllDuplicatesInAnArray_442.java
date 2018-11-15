package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesInAnArray_442 {
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return new ArrayList<Integer>(0);
        }
        
        List<Integer> duplicates = new ArrayList<>(2);
        for (int num : nums) {
            int absNum = Math.abs(num);
            int pos = absNum - 1;
            if (nums[pos] < 0) {
                duplicates.add(absNum);
            } else {
                nums[pos] = -nums[pos];
            }
        }
        return duplicates;
    }
}
