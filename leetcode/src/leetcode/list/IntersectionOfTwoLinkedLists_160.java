package leetcode.list;

public class IntersectionOfTwoLinkedLists_160 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = lengthOf(headA);
        int lenB = lengthOf(headB);
        if (lenA < lenB) {
            ListNode tmp = headA;
            headA = headB;
            headB = tmp;
            
            int len = lenA;
            lenA = lenB;
            lenB = len;
        }
        
        int diffLen = lenA - lenB;
        while (diffLen-- > 0) {
            headA = headA.next;
        }
        
        ListNode intersection = null;
        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
    private int lengthOf(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}
