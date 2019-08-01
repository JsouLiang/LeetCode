package Link.leetcode.easy;

/**
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class LeetCode206 {
    public static ListNode reverseList(ListNode head) {
        ListNode newNextNode = null;
        ListNode currentNode = head;
        ListNode currentNextNode;

        while (currentNode != null) {
            currentNextNode = currentNode.next;
            currentNode.next = newNextNode;
            newNextNode = currentNode;
            currentNode = currentNextNode;
        }
        return newNextNode;
    }
    // TODO: 递归解法
    public static void main(String[] args) {
        ListNode listNode = ListNode.createWithNums(new int[]{1,2,3,4,5});
        LeetCode206.reverseList(listNode);

    }
}
