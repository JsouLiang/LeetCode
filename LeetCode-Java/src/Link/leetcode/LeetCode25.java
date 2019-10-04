package Link.leetcode;

import Link.leetcode.easy.ListNode;

/**
 * LeetCode25 Reverse Nodes in k-Group
 *
 */
public class LeetCode25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode prev = dummyNode;
        while (prev != null) {
            prev = reverseKNodesFrom(prev, k);
        }
        return dummyNode.next;
    }

    /**
     * prev —> node1 -> node2 ... -> nodek -> nodek+1
     * =>
     * prev —> nodek -> nodek - 1 -> ....node2 -> node1 -> nodek+1
     * @param prev
     * @param k
     * @return
     */
    private ListNode reverseKNodesFrom(ListNode prev, int k) {
        // prev —> node1 -> node2 ... -> nodek -> nodek+1
        // 只选转 k 个点
        ListNode current = prev.next;
        ListNode prevNode = prev;
        ListNode node1 = current;
        ListNode nodeK = null;
        ListNode nodeKPlus1 = null;

        // prev <- node1 <- node2 ... <- nodek  nodek+1
        int index = 1;
        while (index <= k) {
            if (current == null) {
                return null;
            }
            ListNode temp = current.next;
            if (index == k) {
                nodeKPlus1 = temp;
                nodeK = current;
            }
            current.next = prevNode;
            prevNode = current;
            current = temp;
            index++;
        }
        // prev -> nodeK->....
        prev.next = nodeK;
        // prev -> nodeK->....node1->nodek+1
        node1.next = nodeKPlus1;
        return nodeKPlus1;
    }

    public static void main(String[] args) {

    }
}
