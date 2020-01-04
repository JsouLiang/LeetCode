package Tree;

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
        // write your code here
        return  null;
    }
}
