package leetcode.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements_347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numToFreqMap = new HashMap<>();
        for (int num : nums) {
            numToFreqMap.put(num, numToFreqMap.getOrDefault(num, 0) + 1);
        }
        
        List<Integer>[] buckets = new List[nums.length + 1]; // Frequency is 1-based
        for (Integer num : numToFreqMap.keySet()) {
            int freq = numToFreqMap.get(num);
            if (buckets[freq] == null) {
                buckets[freq] =  new ArrayList<>();
            }
            buckets[freq].add(num);
        }
        
        List<Integer> results = new ArrayList<>();
        
        for (int pos = buckets.length - 1; pos >= 0 && results.size() < k; pos--) {
            if (buckets[pos] != null) {
                results.addAll(buckets[pos]);
            }
        }
        return results;
    }
}
