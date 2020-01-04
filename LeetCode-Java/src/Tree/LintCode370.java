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
            ///
            if (currentToken.contentEquals("(")) {
                increaseStack.push(currentToken);
            } else {
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

    private int priorityCheck(String token1, String token2) {
        return priorityOfToken(token1) - priorityOfToken(token2);
    }

    private int priorityOfToken(String token) {
        switch (token) {
            case ")":
            case "(":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
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
        lintCode370.convertToRPN(new String[]{"3", "-", "4", "+", "5"});
        lintCode370.convertToRPN(new String[]{"(", "5", "-", "6", ")", "*", "7"});
        /// 15 7 1 1 + − ÷ 3 × 2 1 1 + + −
        lintCode370.convertToRPN(new String[]{"(", "(", "15", "/", "(", "7", "-", "(", "1", "+", "1", ")", ")", ")", "*", "3", ")", "-", "(", "2", "+", "(", "1", "+", "1", ")", ")"});
        lintCode370.convertToRPN(new String[]{"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"});

    }
}
