package leetcode.sorting;

public class WiggleSortII_324 {
    public void wiggleSort(int[] nums) {
        int median = findMedian(nums);
        
        int n = nums.length;
        int i = 0, j = 0, k = n - 1;
        while (j <= k) {
            int tj = fetchIndex(j, n);
            int ti = fetchIndex(i, n);
            int tk = fetchIndex(k, n);
            if (nums[tj] > median) {
                swap(nums, ti, tj);
                i++; j++;
            } else if (nums[tj] < median) {
                swap(nums, tj, tk);
                k--;
            } else {
                j++;
            }
        }
    }
    
    
    private int fetchIndex(int index, int n) {
        return ((index << 1) + 1) % (n | 1);
    }
    
    private int findMedian(int[] nums) {
        int m = nums.length / 2;
        int index = 0;
        int start = 0, end = nums.length - 1;
        while ((index = partition(nums, start, end)) != m) {
            if (index < m) {
                start = index + 1;
            } else {
                end = index - 1;
            }
        }
        return nums[m];
    }
    
    private int partition(int[] nums, int start, int end) {
        int p = nums[start], s = start + 1, e = end;
        while (s <= e) {
            if (nums[s] > p && nums[e] < p) {
                swap(nums, s++, e--);
            }
            if (nums[s] <= p) {
                s++;
            }
            if (nums[e] >= p) {
                e--;
            }
        }
        swap(nums, start, e);
        return e;
    }
    
    private void swap(int nums[], int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
