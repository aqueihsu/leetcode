package leetcode.list;

public class AddTwoNumbersII_445 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode headA, ListNode headB) {
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
        
        ListNode head = new ListNode(0);
        ListNode i = head, j = head;
        
        int diff = lenA - lenB;
        while (headA != null) {
            int sum = headA.val;
            if (diff-- <= 0) {
                sum += headB.val;
                headB = headB.next;
            }
            
            int carryOver = sum / 10;
            int val = sum % 10;
            j = j.next = new ListNode(val);
            
            if (val < 9) {
                if (carryOver > 0) {
                    i.val++;
                    while ((i = i.next) != j) {
                        i.val = 0;
                    }
                } else {
                    i = j;
                }
            }
            headA = headA.next;
        }
        return head.val == 0 ? head.next : head;
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
