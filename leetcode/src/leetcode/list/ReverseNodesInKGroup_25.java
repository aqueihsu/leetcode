package leetcode.list;

public class ReverseNodesInKGroup_25 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        
        ListNode start = head, end = start; // exclusive
        
        ListNode tail = head = new ListNode(0);
        head.next = start;
        
        while (start != null) {
            
            int kk = 0;
            for (; kk < k && end != null; kk++) {
                end = end.next;
            }
            if (kk < k) {
                return head.next;
            }
            tail.next = revert(start, end);
            tail = start;
            start = end;
        }
        return head.next;
    }
    
    private ListNode revert(ListNode start, ListNode end) {
        ListNode head = start;
        start = start.next;
        
        head.next = end;
        while (start != end) {
            ListNode tmp = start.next;
            start.next = head;
            head = start;
            start = tmp;
        }
        return head;
    }
}
