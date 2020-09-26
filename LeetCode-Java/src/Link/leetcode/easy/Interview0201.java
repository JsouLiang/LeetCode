package Link.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

public class Interview0201 {
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode res = head;
        ListNode left, right;
        left = right = head;
        Set<Integer> visited = new HashSet<>();
        visited.add(left.val);
        while (right != null ) {
            if (visited.contains(right.val)) {
                right = right.next;
            } else {
                visited.add(right.val);
                left.next = right;
                left = right;
                right = right.next;
            }
        }
        left.next = null;
        return res;
    }

    public static void main(String[] args) {
        Interview0201 interview0201 = new Interview0201();
        interview0201.removeDuplicateNodes(ListNode.createWithNums(new int[]{1, 2, 3, 3, 2, 1}));
    }
}
