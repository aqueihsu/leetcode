package kth;

import java.util.PriorityQueue;

import leetcode.ListNode;

public class MergeKLists_PriorityQueue {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length,
                (a, b) -> (a.val - b.val));
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        
        ListNode dummy, tail;
        dummy = tail = new ListNode(0);
        
        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
            
            if (tail.next != null) {
                queue.add(tail.next);
            }
        }
        return dummy.next;
    }
    
    private class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int val) {
            this.val = val;
        }
    }
    
    // How does PriorityQueue work?
    // Why is priority queue slower? poll and then add
}

