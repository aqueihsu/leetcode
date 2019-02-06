package leetcode.search;

public class ReversePairs_493 {
    public int reversePairs(int[] nums) {
        return mergeSortAndCount(0, nums.length - 1, nums);
    }
    
    private int mergeSortAndCount(int start, int end, int[] nums) {
        if (start >= end) {
            return 0;
        }
        int mid = (start + end) / 2;
        int count = mergeSortAndCount(start, mid, nums) + mergeSortAndCount(mid + 1, end, nums);
        
        // Count and prep for merge
        // Note: Don't merge "count" and "merge" together. One is "nums[l] > 2 * nums[j]"
        //       One is just nums[l] > nums[j]
        int[] left = new int[mid - start + 1];
        for (int l = start, r = mid + 1, i = 0; l <= mid; l++, i++) {
            left[i] = nums[l];
            
            while (r <= end && nums[l] > ((long) nums[r] << 1L)) {
                r++;
            }
            count += r - mid - 1;
        }
        
        // Merge
        for (int i = start, l = 0, r = mid + 1; i <= end; i++) {
            if (r > end || (l < left.length && left[l] <= nums[r])) {
                nums[i] = left[l++];
            } else {
                // left[l] > nums[r]
                nums[i] = nums[r++];
            }
        }
        return count;
    }
}
