package leetcode.review;

import java.util.PriorityQueue;

public class MergeKSortedLists_23 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        PriorityQueue<ListNode> queue = new PriorityQueue<>((n1, n2) -> (n1.val - n2.val));
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
            
            if (tail.next != null) {
                queue.add(tail.next);
            }
        }
        return dummy.next;
    }
}
