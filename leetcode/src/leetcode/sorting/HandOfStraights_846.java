package leetcode.sorting;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HandOfStraights_846 {
    // Can switch to TreeMap. for (int it : c.keySet()) would sorted.
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length == 0) {
            return W == 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Map<Integer, Integer> counters = new HashMap<>();
        
        for (int card : hand) {
            if (counters.containsKey(card)) {
                counters.put(card, counters.get(card) + 1);
            } else {
                counters.put(card, 1);
                queue.add(card);
            }
        }
        while (!queue.isEmpty()) {
            int firstCard = queue.peek();
            while (counters.get(firstCard) == 0) {
                queue.poll();
                if (queue.isEmpty()) {
                    return true;
                }
                firstCard = queue.peek();
            }
            
            for (int w = 0, card = firstCard; w < W; w++, card++) {
                if (counters.containsKey(card) && counters.get(card) > 0) {
                    counters.put(card, counters.get(card) - 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
