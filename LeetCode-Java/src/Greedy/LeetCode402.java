package Greedy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 402. 移掉K位数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class LeetCode402 {
    //
    public String removeKdigits(String num, int k) {
        char[] chars = num.toCharArray();
        // 为什么使用递增栈
        // 如果当前遇到 4，那么前面比 4 大的数可以移除
        Stack<Character> increaseStack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (!increaseStack.isEmpty()) {
                while (k > 0 && !increaseStack.isEmpty() && increaseStack.peek() > chars[i]) {
                    increaseStack.pop();
                    k--;
                }
            }
            increaseStack.push(chars[i]);
        }
        while (k > 0) {
            increaseStack.pop();
            k--;
        }
        if (increaseStack.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();

        while (!increaseStack.isEmpty()) {
            sb.insert(0, increaseStack.pop());
        }
        while (sb.charAt(0) == '0' && sb.length() > 1) {
            sb.delete(0, 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode402 leetCode402 = new LeetCode402();
        String res = leetCode402.removeKdigits("10", 1);
        System.out.println(res);
    }
}
