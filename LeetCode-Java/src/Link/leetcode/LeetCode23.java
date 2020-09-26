package Link.leetcode;

import Link.leetcode.easy.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode head : lists) {
            if (head != null) {
                queue.add(head);
            }
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode indexNode = dummyNode;
        while (!queue.isEmpty()) {
           ListNode leastNode =  queue.poll();
           indexNode.next = leastNode;
           indexNode = leastNode;
           if (leastNode.next != null) {
               queue.offer(leastNode.next);
           }

        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        LeetCode23 leetCode23 = new LeetCode23();
        ListNode list1 = ListNode.createWithNums(new int[]{});
//        ListNode list2 = ListNode.createWithNums(new int[]{1, 3, 4});
//        ListNode list3 = ListNode.createWithNums(new int[]{2, 6});
        leetCode23.mergeKLists(new ListNode[]{list1});
    }
}
