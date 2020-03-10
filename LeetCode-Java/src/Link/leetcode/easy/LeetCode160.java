package Link.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 160. 相交链表
 */
public class LeetCode160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode headAHead = headA;
        ListNode headBHead = headB;

        while (headA != headB) {
            headA = headA != null ? headA.next : headBHead;
            headB = headB != null ? headB.next : headAHead;
//            headA = headA.next != null ? headA.next : headBHead;
//            headB = headB.next != null ? headB.next : headAHead;
        }
        return headA;
    }
    public static void main(String[] args) {
        LeetCode160 leetCode160 = new LeetCode160();
        ListNode headA = ListNode.createWithNums(new int[]{4, 1, 8, 4, 5});
        ListNode headB = ListNode.createWithNums(new int[]{5, 0, 1, 8, 4, 5});
        ListNode res =  leetCode160.getIntersectionNode(headA, headB);
        System.out.println(res);
    }

}
