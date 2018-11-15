package leetcode.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class TopKFrequentElements_347 {
    private class Pair {
        final int num;
        final int freq;
        
        public Pair(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        PriorityQueue<Pair> queue = new PriorityQueue<>((x, y) -> {return x.freq - y.freq;});
        for (int num : freqMap.keySet()) {
            queue.add(new Pair(num, freqMap.get(num)));
            if (queue.size() > k) {
                queue.remove();
            }
        }
        return queue.stream().map(x -> x.num).collect(Collectors.toList());
    }
}
