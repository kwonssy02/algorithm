package LeetCode._2_Add_Two_Numbers;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode temp = result;
        int alpha = 0;

        while (true) {

            int val1 = (l1 == null ? 0 : l1.val);
            int val2 = (l2 == null ? 0 : l2.val);

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;

            temp.val = (val1 + val2 + alpha);
            if (temp.val >= 10) {
                temp.val -= 10;
                alpha = 1;
            } else
                alpha = 0;

            if(l1 == null && l2 == null) {
                if(alpha == 1)
                    temp.next = new ListNode(1);
                else
                    temp.next = null;
                break;
            }

            temp.next = new ListNode();
            temp = temp.next;
        }

        return result;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        solution.addTwoNumbers(new ListNode(2, new ListNode(4, new ListNode(3))), new ListNode(5, new ListNode(6, new ListNode(4))));
    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}