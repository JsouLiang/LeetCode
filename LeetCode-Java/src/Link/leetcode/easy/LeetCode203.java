package Link.leetcode.easy;

/**
 * 203. 移除链表元素
 * 删除链表中等于给定值 val 的所有节点。
 */
public class LeetCode203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode tempHead = new ListNode(-1);
        tempHead.next = head;
        ListNode pointer = tempHead;
        while (pointer != null && pointer.next != null) {
            ListNode checkedPointer = pointer.next;
            while (checkedPointer != null && checkedPointer.val == val) {
                checkedPointer = checkedPointer.next;
            }
            pointer.next = checkedPointer;
            pointer = checkedPointer;
        }
        return tempHead.next;
    }

    public static void main(String[] args) {
        LeetCode203 leetCode203 = new LeetCode203();
        ListNode head = ListNode.createWithNums(new int[]{6, 1, 2, 6, 3, 4, 5, 6});
        leetCode203.removeElements(head, 6);

        head = ListNode.createWithNums(new int[]{1, 1});
        leetCode203.removeElements(head, 1);
    }
}
