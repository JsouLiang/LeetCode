package Tree;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a string array representing an expression,
 * and return the Reverse Polish notation of this expression. (remove the parentheses)
 * <p>
 * 给定一个字符串数组，它代表一个表达式，返回该表达式的逆波兰表达式。（去掉括号）
 * 样例
 * 样例 1:
 * <p>
 * 输入: ["3", "-", "4", "+", "5"]
 * 输出: ["3", "4", "-", "5", "+"]
 * 解释: 3 - 4 + 5 = -1 + 5 = 4
 * 3 4 - 5 + = -1 5 + = 4
 * <p>
 * 样例 2:
 * 输入: ["(", "5", "-", "6", ")", "*", "7"]
 * 输出: ["5","6","-","7","*"]
 * 解释: (5 - 6) * 7 = -1 * 7 = -7
 * 5 6 - 7 * = -1 7 * = -7
 */
public class LintCode370 {
    public List<String> convertToRPN(String[] expression) {
        // write your code here
        Stack<String> increaseStack = new Stack<>();
        List<String> reversePolishExp = new ArrayList<>();
        for (String currentToken : expression) {
            /// 如果是数字直接记录
            if (isNumber(currentToken)) {
                reversePolishExp.add(currentToken);
                continue;
            }
            /// 左括号直接入栈
            if (currentToken.contentEquals("(")) {
                increaseStack.push(currentToken);
            } else {
                /// 如果递增栈非空，并且栈顶元素的优先级 > 当前元素，出栈，直到栈顶元素优先级 < 当前元素
                while (!increaseStack.isEmpty() && priorityCheck(increaseStack.peek(), currentToken) >= 0) {
                    String peekToken = increaseStack.pop();
                    if (peekToken.contentEquals("(")) {
                        break;
                    }
                    reversePolishExp.add(peekToken);
                }
                if (!currentToken.contentEquals(")")) {
                    increaseStack.push(currentToken);
                }
            }

        }
        while (!increaseStack.isEmpty()) {
            reversePolishExp.add(increaseStack.pop());
        }
        return reversePolishExp;
    }

    /**
     * 比较 toke1 和 token2 的优先级
     */
    private int priorityCheck(String token1, String token2) {
        return priorityOfToken(token1) - priorityOfToken(token2);
    }

    private int priorityOfToken(String token) {
        switch (token) {
            case ")":
                return 0;
            case "(":
                return 1;
            case "+":
            case "-":
                return 2;
            case "*":
            case "/":
                return 3;
        }
        return 0;
    }

    private boolean isNumber(String token) {
        return !token.contentEquals("(") &&
                !token.contentEquals(")") &&
                !token.contentEquals("+") &&
                !token.contentEquals("-") &&
                !token.contentEquals("*") &&
                !token.contentEquals("/");
    }

    public static void main(String[] args) {
        LintCode370 lintCode370 = new LintCode370();
        List<String> res = lintCode370.convertToRPN(new String[]{"3", "-", "4", "+", "5"});
        res = lintCode370.convertToRPN(new String[]{"(", "5", "-", "6", ")", "*", "7"});
        /// 15 7 1 1 + − ÷ 3 × 2 1 1 + + −
        /// ((15 ÷ (7 − (1 + 1))) × 3) − (2 + (1 + 1))
         res = lintCode370.convertToRPN(new String[]{"(", "(", "15", "/", "(", "7", "-", "(", "1", "+", "1", ")", ")", ")", "*", "3", ")", "-", "(", "2", "+", "(", "1", "+", "1", ")", ")"});
        res = lintCode370.convertToRPN(new String[]{"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"});

    }
}
