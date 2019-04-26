package Stack;

import java.util.Stack;


/**
 * 856. 括号的分数
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 *
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *
 *
 * 示例 1：
 *
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 *
 * 输入： "(())"
 * 输出： 2
 * 示例 3：
 *
 * 输入： "()()"
 * 输出： 2
 * 示例 4：
 *
 * 输入： "(()(()))"
 * 输出： 6
 */

public class ScoreOfParentheses {
    /**
     * 使用栈实现
     * 当遇到 ) 时，看栈顶是否是非 ( , 如果是说明在现在是在同一个 () 进行运算，则拿到栈顶值相加
     * 当计算完入栈的时候判断当前 计算的结果，当前结果是 0，说明是一个单纯的() 体，就是1，如果value > 0，说明这是包裹在() 中的值需要 * 2
     * @param S
     * @return
     */
    static public int scoreOfParenthesesUsingStack(String S) {
        Stack<Character> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < S.length(); i++) {
            Character character = S.charAt(i);
            if (character == '(') {
                stack.push(character);
            } else {
                Integer value = 0;
                while (stack.peek() != '(') {
                    value += stack.pop() - '0';
                }
                stack.pop();
                value = value == 0 ? 1 : 2 * value;
                stack.push((char) (value + '0'));
            }
        }
        while (!stack.empty()) {
            int value = stack.pop() - '0';
            result += value;
        }
        return result;
    }



}

