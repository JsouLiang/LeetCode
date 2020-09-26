package Link.leetcode.easy;

public class LeetCode876 {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        LeetCode876 leetCode876 = new LeetCode876();
        ListNode list = ListNode.createWithNums(new int[]{1, 2, 3, 4, 5, 6});
        leetCode876.middleNode(list);
    }
}
