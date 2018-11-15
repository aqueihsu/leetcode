package kth;

public class SplitArrayLargestSum_410 {
    // Move the needle based on the candidate sum v.s. actual sum
    public int splitArray(int[] nums, int m) {
        long l = 0, r = arraySum(nums);
        
        while (l < r) {
            long mid = (l + r) / 2;
            
            if (compareCandidateSum(nums, mid, m) > 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }
    
    // Candidate sum v.s. target sum
    private int compareCandidateSum(int[] nums, long sum, int m) {
        int numSplits = 1;
        long accSum = 0;
        for (int num : nums) {
            if (num > sum) {
                // Candidate sum too small
                return -1;
            }
            if ((accSum + num) <= sum) {
                accSum += num;
            } else {
                accSum = num;
                // Candidate sum too small
                if (++numSplits > m) {
                    return -1;
                }
            }
        }
        // Candidate sum large enough
        return 1;
    }
    
    public int splitArray1(int[] nums, int m) {
        long l = 0, r = arraySum(nums);
        
        while (l < r) {
            long mid = (l + r) / 2;
            
            if (isSumEligible(nums, mid, m)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }
    
    private long arraySum(int[] nums) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
    
    // Can split at most m cuts?
    private boolean isSumEligible(int[] nums, long sum, int m) {
        int numSplits = 1;
        long accSum = 0;
        for (int num : nums) {
            if (num > sum) {
                return false;
            }
            if ((accSum + num) <= sum) {
                accSum += num;
            } else {
                accSum = num;
                if (++numSplits > m) {
                    return false;
                }
            }
        }
        return true;
    }
}
