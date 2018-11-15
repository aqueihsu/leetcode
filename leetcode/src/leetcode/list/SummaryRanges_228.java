package leetcode.list;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges_228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> results = new ArrayList<>();
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[j] != nums[j - 1] && nums[j] != nums[j - 1] + 1) {
                if (nums[i] < nums[j - 1]) {
                    results.add(nums[i] + "->" + nums[j - 1]);
                } else {
                    results.add(String.valueOf(nums[i]));
                }
                i = j;
            }
            j++;
        }
        if (i < j && i < nums.length) {
            if (nums[i] < nums[j - 1]) {
                results.add(nums[i] + "->" + nums[j - 1]);
            } else {
                results.add(String.valueOf(nums[i]));
            }
        }
        return results;
    }
}
