package leetcode.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// If both arrays are too huge to fit in memory, use external sort
public class IntersectionOfTwoArrays_349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> results = new ArrayList<>();
        
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
            int elem1 = nums1[i];
            int elem2 = nums2[j];
            if (elem1 < elem2) {
                i++;
            } else if (elem1 > elem2) {
                j++;
            } else {
                if (results.isEmpty() || elem1 != results.get(results.size() - 1)) {
                    results.add(elem1);
                }
                i++; j++;
            }
        }
        
        int[] resultsArray = new int[results.size()];
        int k = 0;
        for (int result : results) {
            resultsArray[k++] = result;
        }
        return resultsArray;
    }
}
