package Stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class LeetCode20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (stack.isEmpty()) {
                stack.push(chars[i]);
            } else {
                char currentChar = chars[i];
                char topChar = stack.peek();
                if (match(topChar, currentChar)) {
                    stack.pop();
                } else {
                    stack.push(currentChar);
                }
            }
        }
        boolean res = stack.isEmpty();
        return stack.isEmpty();
    }

    private boolean match(char topChar, char currentChar) {
        if (topChar == '(') {
            return currentChar == ')';
        }
        if (topChar == '[') {
            return currentChar == ']';
        }
        if (topChar == '{') {
            return currentChar == '}';
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode20 leetCode20 = new LeetCode20();
        leetCode20.isValid("()[]{}");
        leetCode20.isValid("([])");
        leetCode20.isValid("([)]");
    }
}
