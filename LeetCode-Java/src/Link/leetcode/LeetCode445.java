package Link.leetcode;

import Link.leetcode.easy.ListNode;

import java.util.Stack;

public class LeetCode445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = calculate(l1, l2);
        return res;
    }

    private ListNode calculate(ListNode one, ListNode two) {
        Stack<Integer> value1 = transformToStack(one);
        Stack<Integer> value2 = transformToStack(two);
        ListNode dummyNode = new ListNode(-1);
        int carry = 0;
        while (!value1.isEmpty() && !value2.isEmpty()) {
            int num1 = value1.pop();
            int num2 = value2.pop();
            int res = num1 + num2 + carry;
            carry = res / 10;
            dummyNodeAddNum(dummyNode, res % 10);
        }
        while (!value1.isEmpty()) {
            int num1 = value1.pop();
            int res = num1 + carry;
            carry = res / 10;
            dummyNodeAddNum(dummyNode, res % 10);

        }
        while (!value2.isEmpty()) {
            int num2 = value2.pop();
            int res = num2 + carry;
            carry = res / 10;
            dummyNodeAddNum(dummyNode, res % 10);
        }
        if (carry == 1) {
            dummyNodeAddNum(dummyNode, 1);
        }
        return dummyNode.next;
    }

    void dummyNodeAddNum(ListNode dummyNode , int value) {
        ListNode currentNode = new ListNode(value % 10);
        currentNode.next = dummyNode.next;
        dummyNode.next = currentNode;
    }

    Stack<Integer> transformToStack(ListNode node) {
        ListNode index = node;
        Stack<Integer> stack = new Stack<>();
        while (index != null) {
            stack.push(index.val);
            index = index.next;
        }
        return stack;
    }

    public static void main(String[] args) {
        LeetCode445 leetCode445 = new LeetCode445();
//        ListNode value1 = ListNode.createWithNums(new int[]{7, 2, 4, 3});
//        ListNode value2 = ListNode.createWithNums(new int[]{5, 6, 4});
        ListNode value1 = ListNode.createWithNums(new int[]{0});
        ListNode value2 = ListNode.createWithNums(new int[]{7, 3});
        leetCode445.addTwoNumbers(value1, value2);
    }
}
