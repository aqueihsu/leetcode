package leetcode.list;

public class ReverseLinkedListII_92 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode sprev = dummy;
        int i = 0;
        while (++i < m) {
            sprev = sprev.next;
        }
        ListNode start = sprev.next;
        ListNode eprev = start;
        ListNode end = eprev.next;
        
        while (++i <= n) {
            eprev.next = end.next;
            sprev.next = end;
            end.next = start;
            
            start = sprev.next;
            end = eprev.next;
        }
        return dummy.next;
    }
}
