package leetcode.sorting;

import java.util.ArrayList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf_315 {
    private class Pair {
        int num;
        int pos;
        
        Pair() {}
        
        Pair(int num, int pos) {
            this.num = num;
            this.pos = pos;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Pair> pairs = new ArrayList<>(nums.length);
        List<Pair> auxPairs = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            pairs.add(new Pair(nums[i], i));
            auxPairs.add(new Pair());
        }
        
        int[] counters = new int[nums.length];
        mergeSort(counters, pairs, auxPairs, 0, nums.length - 1);
        
        List<Integer> results = new ArrayList<>(nums.length);
        for (int counter : counters) {
            results.add(counter);
        }
        return results;
    }
    
    private void mergeSort(int[] counters, List<Pair> pairs, List<Pair> auxPairs, int l, int r) {
        if (l >= r) {
            return;
        }
        
        int mid = (l + r) / 2;
        mergeSort(counters, pairs, auxPairs, l, mid);
        mergeSort(counters, pairs, auxPairs, mid + 1, r);
        
        for (int i = l; i <= r; i++) {
            auxPairs.get(i).num = pairs.get(i).num;
            auxPairs.get(i).pos = pairs.get(i).pos;
        }
        
        int i = l, j = mid + 1;
        int k = l;
        int jump = 0;
        while (k <= r) {
            Pair pi = i <= mid ? auxPairs.get(i) : null;
            Pair pj = j <= r ? auxPairs.get(j) : null;
            Pair pk = pairs.get(k);
            
            if (pi == null) {
                pk.num = pj.num;
                pk.pos = pj.pos;
                j++;
            } else if (pj != null && pi.num > pj.num) {
                jump++;
                pk.num = pj.num;
                pk.pos = pj.pos;
                j++;
            } else {
                pk.num = pi.num;
                pk.pos = pi.pos;
                counters[pi.pos] += jump;
                i++;
            }
            k++;
        }
    }
}
