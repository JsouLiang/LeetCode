package 暴力搜索;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 394. Decode String
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class LeetCode394 {
    public String decodeString(String s) {
        String res = extendString(s);
        System.out.println(res);
       return res;
    }
    private String extendString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String res = "";
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length();) {
            final char currentChar = s.charAt(i++);
            if (isNumber(currentChar)) {
                String count = Character.toString(currentChar);
                while (isNumber(s.charAt(i))) {
                    count += Character.toString(s.charAt(i++));
                }
                stack.push(s.charAt(i++));
                String subString = "";
                while(true) {
                    if (s.charAt(i) == '[') {
                        stack.push(s.charAt(i));
                    } else if (s.charAt(i) == ']') {
                        stack.pop();
                    }
                    if (stack.isEmpty()) {
                        i++;
                        break;
                    } else {
                        subString += Character.toString(s.charAt(i++));
                    }
                }
                subString = extendString(subString);
                String repeatedSubStr = "";
                for (int j = 0; j < Integer.parseInt(count); j++) {
                    repeatedSubStr += subString;
                }
                res += repeatedSubStr;
            } else {
                res += currentChar;
            }
        }
        return res;
    }

    private boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }


    private boolean isLetter(char c) {
        return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';
    }

    public static void main(String[] args) {
        LeetCode394 leetCode394 = new LeetCode394();
        leetCode394.decodeString("100[leetcode]");
        leetCode394.decodeString("3[a]");
        leetCode394.decodeString("3[a2[c]]");
        leetCode394.decodeString("3[a]2[bc]");
        leetCode394.decodeString("2[abc]3[cd]ef");
    }
}
