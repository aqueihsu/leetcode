package leetcode.review;

public class SearchInRotatedSortedArray_33 {
    // [4,5,6,7,0,1,2]
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[l] <= nums[m]) {
                // Left is sorted
                if (target < nums[l] || nums[m] < target) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            } else {
                // nums[r] <= nums[m]
                // Right is sorted
                if (target < nums[m] || nums[r] < target) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }
        return -1;
    }
    public int search_v1(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] < target) {
                if (nums[l] < nums[m]) {
                    // Sorted
                    l = m + 1;
                } else {
                    // nums[m] < nums[l]: not sorted
                    if (nums[r] == target) {
                        return r;
                    }
                    if (target < nums[r]) {
                        l = m + 1;
                    } else {
                        // nums[r] < target
                        r = m - 1;
                    }
                }
            } else {
                // target < nums[m]
                if (nums[m] < nums[r]) {
                    // Sorted
                    r = m - 1;
                } else {
                    // nums[r] < nums[m]: not sorted
                    if (nums[l] == target) {
                        return l;
                    }
                    if (target < nums[l]) {
                        l = m + 1;
                    } else {
                        // nums[l] < target
                        r = m - 1;
                    }
                }
            }
        }
        return -1;
    }
}
