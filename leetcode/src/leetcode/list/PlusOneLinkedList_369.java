package leetcode.list;

public class PlusOneLinkedList_369 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode i = dummy, j = dummy;
        dummy.next = head;
        while ((j = j.next) != null) {
            if (j.val < 9) {
                i = j;
            }
        }
        i.val++;
        while ((i = i.next) != null) {
            i.val = 0;
        }
        return dummy.val != 0 ? dummy : dummy.next;
    }
}
