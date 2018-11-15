package leetcode.list;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges_163 {
    // Careful of the overflow! For all number questions!
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> results = new ArrayList<String>();
        long curNum = lower;
        for (int num : nums) {
            if (curNum < num) {
                if (curNum < num - 1) {
                    results.add(curNum + "->" + (num - 1));
                } else if (curNum == num - 1) {
                    results.add(String.valueOf(curNum));
                }
            }
            if (num >= upper) {
                return results;
            }
            curNum = num == upper ? num : (num + 1);
        }
        
        if (curNum == upper) {
            results.add(String.valueOf(upper));
        } else if (curNum < upper) {
            results.add(curNum + "->" + upper);
        }
        return results;
    }
}
