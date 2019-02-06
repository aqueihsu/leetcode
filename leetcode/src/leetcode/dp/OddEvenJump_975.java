package leetcode.dp;

import java.util.TreeMap;

public class OddEvenJump_975 {
    public int oddEvenJumps(int[] A) {
        int n = A.length;
        if (n <= 0) {
            return 0;
        }
        
        // Insert the numbers from the end (guaranteed to be "next" number to jump to
        TreeMap<Integer, Integer> valToIdx = new TreeMap<>();
        valToIdx.put(A[n - 1], n - 1);
        
        boolean[] even = new boolean[n]; // i-th element as even jump
        boolean[] odd = new boolean[n];
        even[n - 1] = odd[n - 1] = true;
        
        for (int idx = n - 2; idx >= 0; idx--) {
            int val = A[idx];
            if (valToIdx.containsKey(val)) {
                // Similar value is already added. This will be the next jump
                int idxNext = valToIdx.get(val);
                odd[idx] = even[idxNext];
                even[idx] = odd[idxNext];
            } else {
                // Odd jump: Find the next bigger or equal number to jump to
                // Even jump: Find the next smaller or equal number to jump to
                Integer valNextOddJump = valToIdx.higherKey(val);
                even[idx] = valNextOddJump == null ? false : odd[valToIdx.get(valNextOddJump)];
                
                Integer idxNextEvenJump = valToIdx.lowerKey(val);
                odd[idx] = idxNextEvenJump == null ? false : even[valToIdx.get(idxNextEvenJump)];
            }
            valToIdx.put(val, idx);
        }
        
        int count = 0;
        for (boolean e : even) {
            if (e) {
                count++;
            }
        }
        return count;
    }
}
