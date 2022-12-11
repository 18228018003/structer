package leetcode;

/**
 * 给定一个链表判断是否是环形链表
 */
public class _141_CycleList {
    public boolean hasCycle(ListNode head){
        if (head == null || head.next == null) return false;
        ListNode f =  head.next;
        ListNode s =  head;
        while (f != null && f.next != null){
            if (s == f) return true;
            f = f.next.next;
            s = s.next;
        }
        return false;
    }

    private static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x) {
            val= x;
        }
    }
}
