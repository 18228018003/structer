package leetcode;

/**
 * 反转链表
 */
public class _206_ReverseList {
    public static ListNode reverseList1(ListNode head){
        ListNode prev = null;
        ListNode node = head;
        ListNode next = null;
        while (node != null){
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
    public static ListNode reverseList(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode node = reverseList(head);
    }
    private static class ListNode{
        public int val;
        public ListNode next;
        public ListNode(int x) {
            val= x;
        }
    }
}
