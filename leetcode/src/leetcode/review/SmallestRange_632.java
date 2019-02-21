package leetcode.review;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange_632 {
    private class Pair {
        int iList;
        int val;
        Pair(int iList, int val) {
            this.iList = iList;
            this.val = val;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        Iterator<Integer>[] itors = new Iterator[nums.size()];
        PriorityQueue<Pair> queue = new PriorityQueue<>((p1, p2) -> (p1.val - p2.val));
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = nums.get(i);
            itors[i] = list.iterator();
            
            if (itors[i].hasNext()) {
                queue.add(new Pair(i, itors[i].next()));
            }
        }
        
        int valStart = 0, valEnd = Integer.MAX_VALUE;
        
        Deque<Pair> sortedList = new LinkedList<>();
        int[] count = new int[nums.size()];
        int nDistinctLists = 0;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            sortedList.addLast(pair);
            
            if (count[pair.iList] == 0) {
                nDistinctLists++;
            }
            count[pair.iList]++;
            
            while (nDistinctLists == nums.size()) {
                Pair front = sortedList.pollFirst();
                count[front.iList]--;
                if (count[front.iList] == 0) {
                    if (pair.val - front.val < valEnd - valStart) {
                        valEnd = pair.val;
                        valStart = front.val;
                    }
                    nDistinctLists--;
                }
            }
            
            if (itors[pair.iList].hasNext()) {
                queue.add(new Pair(pair.iList, itors[pair.iList].next()));
            }
        }
        return new int[] {valStart, valEnd};
    }
}
