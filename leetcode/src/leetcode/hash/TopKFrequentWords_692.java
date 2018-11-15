package leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords_692 {
    private class Pair {
        final String word;
        final int freq;
        public Pair(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        
        PriorityQueue<Pair> queue = new PriorityQueue<>(
                (x, y) -> {
                    if (x.freq == y.freq) {
                        // keep the maximum at the top
                        return y.word.compareTo(x.word);
                    }
                    // keep the minimum at the top
                    return x.freq - y.freq;
                });
        for (String word : freqMap.keySet()) {
            queue.add(new Pair(word, freqMap.get(word)));
            if (queue.size() > k) {
                queue.remove();
            }
        }
        
        String[] results = new String[k];
        while (!queue.isEmpty()) {
            results[--k] = queue.remove().word;
        }
        return Arrays.asList(results);
    }
}
