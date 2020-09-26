package DoublePoint.SlidingWindow.hard;

/**
 * 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 */
public class LeetCode32 {
    // TODO: 栈 解法
    // TODO: 动态规划 解法

    public int longestValidParentheses(String s) {
        /// 1. 扫描下原串
        /// (((()) 如果 ( +1, )-1 这种情况会导致结果为0，故还需要将括号串逆序再次扫描
        int original = validParenth(s);
        /// 2. 括号串逆序再次扫描
        /// reverse: ))((((
        /// trans:   (())))
        String reversedParenthese = new StringBuffer(s).reverse().toString();
        String transedParenthese = "";
        for (int i = 0; i < reversedParenthese.length();i++) {
            Character character = reversedParenthese.charAt(i);
            if (character == '(') {
                transedParenthese += ")";
            } else {
                transedParenthese += "(";
            }
        }
        int transedVal = validParenth(transedParenthese);
        return Math.max(original, transedVal);
    }

    private int validParenth(String s) {
        int count = 0;
        int res = 0;
        for (int right = 0, left = 0; right < s.length(); right++) {
            Character parenthese = s.charAt(right);
            /// 左括号 + 1
            if (parenthese == '(') {
                count++;
            } else {
                /// 右括号 -1
                count--;
                /// count < 0 表示当前 right 位置之前无法组成合法的括号序列
                /// 将 left 移动至当前 right 下个括号
                if (count < 0) {
                    left = right + 1;
                    count = 0;
                } else if (count == 0) {
                    /// left ~ right 是合法的括号序列区间
                    res = Math.max(res, right - left + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode32 test = new LeetCode32();
        int result = test.longestValidParentheses("(((()))");
        result = test.longestValidParentheses(")()()");
    }
}
