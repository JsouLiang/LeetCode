package Link.leetcode.easy;

public class LeetCode141 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        // 快慢指针，while 到 fast == slow
        while (slow != fast) {
            /// 如果到达 fast 无法继续，则表示没有环
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        String s = "";

        return true;
    }

    public static void main(String[] args) {
        LeetCode141 leetCode141 = new LeetCode141();
        ListNode l1 = new ListNode(1);
        l1.next = l1;
        leetCode141.hasCycle(l1);
    }
}
