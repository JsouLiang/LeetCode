package Link.leetcode.medium;

import Link.leetcode.easy.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 */
public class LeetCode24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        head = help(dummyNode);
        return head.next;
    }

    private ListNode help(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        help(node.next.next);
        if (node.next.next != null) {
            ListNode temp = node.next;
            node.next = node.next.next;
            temp.next = node.next.next;
            node.next.next = temp;
        }
        return node;
    }

    public static void main(String[] args) {
        LeetCode24 leetCode24 = new LeetCode24();
        ListNode list = ListNode.createWithNums(new int[]{1, 2, 3, 4});
        leetCode24.swapPairs(list);
        list = ListNode.createWithNums(new int[]{1, 2});
        leetCode24.swapPairs(list);

        list = ListNode.createWithNums(new int[]{1, 2, 3});
        leetCode24.swapPairs(list);
    }
}
