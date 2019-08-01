package Link.leetcode.medium;

import Link.leetcode.easy.LeetCode206;
import Link.leetcode.easy.ListNode;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class LeetCode92 {
    /**
     * 虚拟头结点解法
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode headNode = new ListNode(-1);
        headNode.next = head;

        int index = 1;
        ListNode breakedNode = headNode;

        while (index < m) {
            breakedNode = breakedNode.next;
            index++;
        }

        ListNode currentNode = breakedNode.next;
        ListNode nextNode = currentNode.next;
        ListNode temp = null;
        index = m;
        while (index < n && nextNode != null) {
            temp = nextNode.next;
            nextNode.next = currentNode;
            currentNode = nextNode;
            nextNode = temp;
            index++;
        }

        breakedNode.next.next = nextNode;
        breakedNode.next = currentNode;
        return headNode.next;
    }
    // TODO: 递归解法

    public static void main(String[] args) {
        ListNode listNode = ListNode.createWithNums(new int[]{1,2,3,4,5});
//        LeetCode92.reverseBetween(listNode, 2, 4);

        listNode = ListNode.createWithNums(new int[]{5});
//        LeetCode92.reverseBetween(listNode, 1, 1);

        listNode = ListNode.createWithNums(new int[]{3, 5});
        LeetCode92.reverseBetween(listNode, 1, 2);
    }

}
