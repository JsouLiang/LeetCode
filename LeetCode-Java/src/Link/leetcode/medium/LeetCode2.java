package Link.leetcode.medium;

import Link.leetcode.easy.ListNode;

/**
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class LeetCode2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return  l2;
        if (l2 == null) return l1;

        ListNode res = null, resHead = null;
        int carryValue = 0;
        while (l1 != null && l2 != null) {
            int value = l1.val + l2.val + carryValue;
            ListNode currentVal = new ListNode(value % 10);
            carryValue = value / 10;
            if (resHead == null) {
                res = currentVal;
                resHead = res;
            } else {
                res.next = currentVal;
                res = res.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int value = l1.val + carryValue;
            ListNode currentVal = new ListNode(value % 10);
            carryValue = value / 10;
            res.next = currentVal;
            res = res.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int value = l2.val + carryValue;
            ListNode currentVal = new ListNode(value % 10);
            carryValue = value / 10;
            res.next = currentVal;
            res = res.next;
            l2 = l2.next;
        }
        if (carryValue != 0) {
            res.next = new ListNode(1);
        }
        return resHead;
    }



    public static void main(String[] args) {
        LeetCode2 leetCode2 = new LeetCode2();
        ListNode l1 = ListNode.createWithNums(new int[]{2, 4, 3});
        ListNode l2 = ListNode.createWithNums(new int[]{5, 6, 4});
        ListNode res = leetCode2.addTwoNumbers(l1, l2);
        l1 = ListNode.createWithNums(new int[]{9, 9, 9});
        l2 = ListNode.createWithNums(new int[]{1});
        res = leetCode2.addTwoNumbers(l1, l2);
        System.out.println(res);
    }
}
