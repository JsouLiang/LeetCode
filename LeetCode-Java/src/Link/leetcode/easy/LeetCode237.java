package Link.leetcode.easy;

public class LeetCode237 {
    // 4 -> 5 -> 1 -> 9
    // 删除 5
    // 因为我们无法拿到节点 4
    // 所以我们可以修改 5 这个节点
    // 1. 将 5 节点的值设置成 5 下一个节点的值：1
    // 2. 将 5 节点的 next 设置成 1 的 next
    // 上面两个操作即将 1 删掉，同时将 1 的设置设置到前面
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
