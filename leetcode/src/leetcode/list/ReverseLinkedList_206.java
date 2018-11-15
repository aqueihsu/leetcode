package leetcode.list;

public class ReverseLinkedList_206 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode sprev = dummy;
        ListNode start = sprev.next;
        ListNode eprev = start;
        ListNode end = start.next;
        while (end != null) {
            eprev.next = end.next;
            sprev.next = end;
            end.next = start;
            
            start = sprev.next;
            end = eprev.next;
        }
        return dummy.next;
    }
}
