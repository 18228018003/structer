package leetcode;

/**
 * 删除链表元素
 */
public class _237_removeListNode {
    public void removeListNode(ListNode node){
        node.val = node.next.val;
        node.next = node.next.next;
    }
    private static class ListNode{
        public int val;
        public ListNode next;
        public ListNode(int x) {
            val= x;
        }
    }
}
