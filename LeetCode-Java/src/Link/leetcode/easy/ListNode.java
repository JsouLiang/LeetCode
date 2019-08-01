package Link.leetcode.easy;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public static ListNode createWithNums(int[] nums) {
        ListNode head = null;
        ListNode index = head;
        for (int i = 0; i < nums.length; i++) {
            ListNode current = new ListNode(nums[i]);
            if (index != null) {
                index.next = current;
            } else {
                head = current;
            }
            index = current;
        }
        return head;
    }
}
