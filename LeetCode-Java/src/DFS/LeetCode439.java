package DFS;

import java.util.Stack;

/**
 * 439. 三元表达式解析器
 * 给定一个以字符串表示的任意嵌套的三元表达式，计算表达式的值。
 * 你可以假定给定的表达式始终都是有效的并且只包含数字 0-9, ?, :, T 和 F (T 和 F 分别表示真和假）。
 *
 */
public class LeetCode439 {
    public String parseTernary(String expression) {
        String res = calculate(expression);
        return res;
    }

    /**
     * 递归解法
     */
    private String calculate(String expression) {
        if (!expression.contains("?")) {
            return expression;
        }
        StringBuffer condition = new StringBuffer();
        char[] expressionChars = expression.toCharArray();
        int index = 0;
        for (index = 0; index < expressionChars.length; index++) {
            if (expressionChars[index] == '?') {
                break;
            }
            condition.append(expressionChars[index]);
        }

        StringBuffer leftRes = new StringBuffer();
        StringBuffer rightRes = new StringBuffer();

        Stack<Character> conditions = new Stack<>();
        StringBuffer currentStringBuffer = leftRes;
        for (index++ ;index < expressionChars.length; index++) {
            Character currentChar = expressionChars[index];
            if (currentChar == ':') {
                if (!conditions.isEmpty()) {
                    conditions.pop();
                } else {
                    currentStringBuffer = rightRes;
                    continue;
                }
            }
            else if (currentChar == '?') {
                conditions.add('?');
            }
            currentStringBuffer.append(expressionChars[index]);
        }
        if (condition.toString().equals("T")) {
            return calculate(leftRes.toString());
        } else {
            return calculate(rightRes.toString());
        }
    }

    /**
     * TODO: 使用栈解法
     * @param expression
     * @return
     */
    private String calculateWithStack(String expression) {
        return null;
    }

    public static void main(String[] args) {
        LeetCode439 leetCode439 = new LeetCode439();
        leetCode439.parseTernary("T?T?F:5:3");
    }
}
