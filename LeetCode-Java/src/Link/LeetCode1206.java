package Link;

import java.util.*;

/**
 * 1206. 设计跳表
 */
public class LeetCode1206 {

    class SkipListNode {
        int value;
        SkipListNode next;
        SkipListNode down;

        public SkipListNode(int value) {
            this(value, null, null);
        }

        public SkipListNode(int value, SkipListNode next, SkipListNode down) {
            this.value = value;
            this.next = next;
            this.down = down;
        }
    }

    private SkipListNode head;

    public LeetCode1206() {
        head = new SkipListNode(-1);
    }

    /**
     * 返回 target 是否在跳表中
     * @param target
     * @return
     */
    public boolean search(int target) {
        SkipListNode node = head;
        while (node != null) {
            // 往右找
            while (node.next != null && node.next.value < target) {
                node = node.next;
            }
            // 找到 target
            if (node.next != null && node.next.value == target) {
                return true;
            }
            // 向下
            node = node.down;
        }
        return false;
    }

    /**
     * 向跳表中添加元素
     * @param num
     */
    public void add(int num) {
        // 在寻找的过程中向下时，每层最后一个元素
        List<SkipListNode> downPath = new ArrayList<>();
        SkipListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.next.value < num) {
                cur = cur.next;
            }
            downPath.add(cur);
            cur = cur.down;
        }
        // 当前层是否添加上新来的节点，这里每层都有 50% 概览插入，直到不插入
        boolean currentLevelAdd = true;
        // 新插入节点时，用于建立 down path
        SkipListNode downNode = null;
        while (currentLevelAdd && !downPath.isEmpty()) {
            // 最底层元素
            SkipListNode lowestLevelNode = downPath.remove(downPath.size() - 1);
            lowestLevelNode.next = new SkipListNode(num, lowestLevelNode.next, downNode);
            downNode = lowestLevelNode.next;
            currentLevelAdd = ((int)Math.random() & 1) == 0;
        }
    }

    /**
     * 从跳表中删除一个元素，如果该值不在跳表中，直接返回 false，
     * 如果存在多个，删除其中任意一个即可
     * @param num
     * @return
     */
    public boolean erase(int num) {
        SkipListNode cur = head;
        boolean finded = false;
        while (cur != null) {
            while (!finded && cur.next != null && cur.next.value < num) {
                cur = cur.next;
            }
            // 找到 num
            if (null != cur.next && cur.next.value == num) {
                finded = true;
                cur.next = cur.next.next;
            }
            // 继续向下
            // 如果没找到，则继续向下从下层继续找
            // 如果找到，则将下层的 num 节点删除
            cur = cur.down;
        }
        return finded;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> firstGreaterInfo = new HashMap<>();
        // 递减栈，找到右边第一个比当前元素大的位置
        Stack<Integer> decreaseStack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            if (!decreaseStack.isEmpty()) {
                // 当前元素大于栈顶元素 pop 栈顶元素
                while (!decreaseStack.isEmpty() && nums2[i] > nums2[decreaseStack.peek()]) {
                    // 栈顶元素第一个大于它的值是 num2[i];
                    firstGreaterInfo.put(nums2[decreaseStack.peek()], nums2[i]);
                    decreaseStack.pop();
                }
            }
            decreaseStack.push(i);
        }
        // 还在 decreaseStack 中的元素说明右边没有比它大的元素
        while (!decreaseStack.isEmpty()) {
            firstGreaterInfo.put(nums2[decreaseStack.pop()], -1);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = firstGreaterInfo.get(nums1[i]);
        }
        return nums1;
    }

    public static void main(String[] args) {
        LeetCode1206 skiplist = new LeetCode1206();
        skiplist.nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4});

    }
}
