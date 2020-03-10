package Link.leetcode.medium;

import Link.leetcode.easy.ListNode;

/**
 * 86. 分隔链表
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置
 */
public class LeetCode86 {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode lessIndex = new ListNode(-1);
        ListNode lessHead = lessIndex;
        ListNode greatIndex = new ListNode(-1);
        ListNode greatHead = greatIndex;
        while (head != null) {
            ListNode current = head;
            head = head.next;
            if (current.val < x) {
                lessIndex.next = current;
                lessIndex = lessIndex.next;
            } else {
                greatIndex.next = current;
                greatIndex = greatIndex.next;
                greatIndex.next = null;
            }
        }
        lessIndex.next = greatHead.next;
        return lessHead.next;
    }

    public static void main(String[] args) {
        LeetCode86 leetCode86 = new LeetCode86();
        ListNode head = ListNode.createWithNums(new int[]{1, 4, 3, 2, 5, 2});
        leetCode86.partition(head, 3);
    }
}
