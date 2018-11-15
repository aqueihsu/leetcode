package leetcode.design;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ZigzagIterator_281 {
    Deque<Iterator<Integer>> queue;
    
    public ZigzagIterator_281(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<>();
        if (!v1.isEmpty()) {
            queue.addLast(v1.iterator());
        }
        if (!v2.isEmpty()) {
            queue.addLast(v2.iterator());
        }
    }

    public int next() {
        Iterator<Integer> itor = queue.pollFirst();
        int value = itor.next();
        if (itor.hasNext()) {
            queue.addLast(itor);
        }
        return value;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
