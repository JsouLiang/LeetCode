package DivideAndConquer;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 *
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。
 * 你需要给出所有可能的组合的结果。有效的运算符号包含 +,-以及*。
 *
 * 示例 1:
 *
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * 示例 2:
 *
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
public class LeetCode241 {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = calculate(input);
        System.out.println(res);
        return res;
    }

    private List<Integer> calculate(String input) {
        try {
            List<Integer> res = new ArrayList<>();
            res.add(Integer.parseInt(input));
            return res;
        } catch (NumberFormatException e) {
            char[] chars = input.toCharArray();
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                char currentChar = chars[i];
                if (isOperator(currentChar)) {
                    String left = input.substring(0, i);
                    String right = input.substring(i + 1);
                    List<Integer> leftVals = calculate(left);
                    List<Integer> rightVals = calculate(right);
                    for (int leftI = 0; leftI < leftVals.size(); leftI++) {
                        for (int rightI = 0; rightI < rightVals.size(); rightI++) {
                            int cRes = calculate(leftVals.get(leftI), currentChar, rightVals.get(rightI));
                            res.add(cRes);
                        }
                    }
                }
            }
            return res;
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*';
    }

    private int calculate(int left, char op,  int right) {
        switch (op) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode241 leetCode241 = new LeetCode241();
//        leetCode241.diffWaysToCompute("2+1*2");
        leetCode241.diffWaysToCompute("2*3-4*5");
    }
}
