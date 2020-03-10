package Tree;

import jdk.nashorn.api.tree.ExpressionTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 表达树是一个二叉树的结构，用于衡量特定的表达。所有表达树的叶子都有一个数字字符串值。而所有表达树的非叶子都有另一个操作字符串值。
 *
 * 给定一个表达数组，请构造该表达的表达树，并返回该表达树的根。
 *
 * 样例
 * 样例 1:
 *
 * 输入: ["2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"]
 * 输出: {[-],[*],[/],[2],[6],[+],[+],#,#,#,#,[23],[7],[1],[2]}
 * 解释:
 * 其表达树如下：
 *
 * 	                 [ - ]
 * 	             /          \
 * 	        [ * ]              [ / ]
 * 	      /     \           /         \
 * 	    [ 2 ]  [ 6 ]      [ + ]        [ + ]
 * 	                     /    \       /      \
 * 	                   [ 23 ][ 7 ] [ 1 ]   [ 2 ] .
 *
 * 在构造该表达树后，你只需返回根节点`[-]`。
 * 样例 2:
 *
 * 输入: ["10","+","(","3","-","5",")","+","7","*","7","-","2"]
 * 输出: {[-],[+],[2],[+],[*],#,#,[10],[-],[7],[7],#,#,[3],[5]}
 * 解释:
 * 其表达树如下：
 *  	                       [ - ]
 * 	                   /          \
 * 	               [ + ]          [ 2 ]
 * 	           /          \
 * 	       [ + ]          [ * ]
 *               /     \         /     \
 * 	  [ 10 ]  [ - ]    [ 7 ]   [ 7 ]
 * 	          /    \
 *                 [3]    [5]
 * 在构造该表达树后，你只需返回根节点`[-]`。
 */
public class ExpressionTreeBuild {
    class ExpressionTreeNode {
        public String symbol;
        public ExpressionTreeNode left, right;

        public ExpressionTreeNode(String symbol) {
            this.symbol = symbol;
            this.left = this.right = null;
        }
    }

    public ExpressionTreeNode build(String[] expression) {
        List<String> rpn = convertToRPN(expression);
        Stack<ExpressionTreeNode> expressionTreeNodeStack = new Stack<>();
        for (int i = 0; i < rpn.size(); i++) {
            ExpressionTreeNode node = new ExpressionTreeNode(rpn.get(i));
            if (!isNumber(rpn.get(i))) {
                ExpressionTreeNode right = expressionTreeNodeStack.pop();
                ExpressionTreeNode left = expressionTreeNodeStack.pop();
                node.left = left; node.right = right;
            }
            expressionTreeNodeStack.push(node);
        }
        if (expressionTreeNodeStack.isEmpty()) {
            return null;
        }
        return expressionTreeNodeStack.pop();
    }

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
        ExpressionTreeBuild expressionTree = new ExpressionTreeBuild();
        expressionTree.build(new String[]{"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"});
        expressionTree.build(new String[]{"(","(","(","(","(",")",")",")",")",")"});

    }

}
