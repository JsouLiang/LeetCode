package Link.leetcode.easy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 面试题62. 圆圈中最后剩下的数字
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，
 * 因此最后剩下的数字是3。
 *
 *
 */
public class Interview62 {
    class Link {
        class Node {
            int val;
            Node next;

            public Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }

            public Node(int val) {
                this(val, null);
            }
        }
        Node head;
        int nodeCount;
        public Link(int n) {
            nodeCount = n;
            Node dummyNode = new Node(-1);
            Node index = dummyNode;
            for (int val = 0; val < n; val++){
                Node currentNode = new Node(val);
                index.next = currentNode;
                index = currentNode;
            }
            index.next = dummyNode.next;
            head = dummyNode.next;
        }

        int delete(Node node) {
            nodeCount--;
            int deletedVal = node.val;
            node.val = node.next.val;
            node.next = node.next.next;
            return deletedVal;
        }
    }

    public int lastRemaining(int n, int m) {
        Link link = new Link(n);
        Link.Node index = link.head;
        List<Integer> deletedVals = new ArrayList<>();
        int count = 1;
        while (link.nodeCount > 1) {
            if (count < m) {
                count++;
                index = index.next;
                continue;
            }
            int deletedVal = link.delete(index);
            deletedVals.add(deletedVal);
            count = 1;
        }
        return index.val;
    }

    public int lastRemainingII(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int len = m; int currentIndex = 0;
        List<Integer> deletedVals = new ArrayList<>();
        while (n > 1) {
            int removedIndex = (currentIndex + m - 1) % n;
            deletedVals.add(list.remove(removedIndex));
            currentIndex = removedIndex;
            n--;
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        Interview62 interview62 = new Interview62();
        interview62.lastRemainingII(5, 3);
        interview62.lastRemainingII(10, 17);
    }
}
