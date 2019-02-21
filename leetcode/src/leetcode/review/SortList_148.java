package leetcode.review;

public class SortList_148 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    
    public ListNode sortList(ListNode head) {
        ListNode list1 = head;
        ListNode list2 = split(head);
        
        if (list2 == null) {
            return list1;
        }
        list1 = sortList(list1);
        list2 = sortList(list2);
        
        // Merge sorted lists
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (list1 != null || list2 != null) {
            if (list2 == null || (list1 != null && list1.val <= list2.val)) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        return dummy.next;
    }
    
    private ListNode split(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // Return the mid point
        ListNode mid = slow.next;
        slow.next = null;
        
        return mid;
    }
}
