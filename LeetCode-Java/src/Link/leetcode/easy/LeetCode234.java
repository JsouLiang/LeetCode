package Link.leetcode.easy;

import java.util.List;

/**
 * 234. 回文链表
 */
public class LeetCode234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;
        ///
        ListNode middleNode = findMiddleNode(head);
        ListNode reversedHalfList = reverseList(middleNode.next);
        boolean res = compareList(head, reversedHalfList);
        return res;
    }

    private ListNode findMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode index = head, newNext = null;
        while (index != null) {
            ListNode currentNode = index;
            index = currentNode.next;
            currentNode.next = newNext;
            newNext = currentNode;
        }
        return newNext;
    }

    boolean compareList(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode234 leetCode234 = new LeetCode234();
        ListNode head = ListNode.createWithNums(new int[]{1, 2, 3, 2, 1});
        leetCode234.isPalindrome(head);
        head = ListNode.createWithNums(new int[]{1, 2});
        leetCode234.isPalindrome(head);

        head = ListNode.createWithNums(new int[]{1, 2, 1});
        leetCode234.isPalindrome(head);
    }
}
