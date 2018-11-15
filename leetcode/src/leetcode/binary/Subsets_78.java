package leetcode.binary;

import java.util.ArrayList;
import java.util.List;

public class Subsets_78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> sets = new ArrayList<>();
        int n = 1 << nums.length;
        while (--n >= 0) {
            List<Integer> set = new ArrayList<>();
            int nCopy = n;
            int i = 0;
            while (nCopy > 0) {
                if ((nCopy & 1) != 0) {
                    set.add(nums[i]);
                }
                i++;
                nCopy >>= 1;
            }
            sets.add(set);
        }
        return sets;
    }
}
