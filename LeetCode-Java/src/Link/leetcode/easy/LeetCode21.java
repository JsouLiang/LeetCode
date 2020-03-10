package Link.leetcode.easy;

public class LeetCode21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode indexNode = dummyNode;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                indexNode.next = l1;
                l1 = l1.next;
            } else {
                indexNode.next = l2;
                l2 = l2.next;
            }
            indexNode = indexNode.next;
        }
        if (l1 != null) {
            indexNode.next = l1;
        } else if (l2 != null){
            indexNode.next = l2;
        }
        return dummyNode.next;
    }
    public static void main(String[] args) {
        LeetCode21 leetCode21 = new LeetCode21();
        ListNode l1 = ListNode.createWithNums(new int[]{1, 2, 4});
        ListNode l2 = ListNode.createWithNums(new int[]{1, 3, 4});
        leetCode21.mergeTwoLists(l1, l2);
    }
}
