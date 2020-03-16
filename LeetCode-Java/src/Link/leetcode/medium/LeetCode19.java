package Link.leetcode.medium;

import Link.leetcode.easy.ListNode;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 */
public class LeetCode19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode fast = dummyNode;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        ListNode slow = dummyNode;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyNode.next;
    }

    public static void main(String[] args) {
        LeetCode19 leetCode19 = new LeetCode19();
        ListNode link = ListNode.createWithNums(new int[]{1, 2, 3, 4, 5});
//        leetCode19.removeNthFromEnd(link, 2);
        leetCode19.removeNthFromEnd(link, 5);
    }
}
