package kth;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements_658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return new ArrayList<>(0);
        }
        
        if (k >= arr.length) {
            return intArrayToList(arr, 0, arr.length);
        }
        
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (x - arr[m] > arr[m + k] - x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return intArrayToList(arr, l, l + k);
    }
    
    public List<Integer> findClosestElements_sol2(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return new ArrayList<>(0);
        }
        
        if (k >= arr.length) {
            return intArrayToList(arr, 0, arr.length);
        }
        
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] <= x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        
        int posX = r - 1;
        l = posX;
        r = l + 1;
        while (k > 0 && l >= 0 && r < arr.length) {
            int distL = x - arr[l];
            int distR = arr[r] - x;
            if (distL <= distR) {
                l--;
            } else {
                r++;
            }
            k--;
        }
        
        if (k > 0) {
            if (l > 0) {
                l -= k;
            } else {
                r += k;
            }
        }
        
        return intArrayToList(arr, l + 1, r);
    }
    
    private List<Integer> intArrayToList(int[] arr, int l, int r) {
        List<Integer> list = new ArrayList<>(r - l);
        for (int i = l; i < r; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
