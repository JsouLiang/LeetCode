package 暴力搜索;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 784. Letter Case Permutation
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string. 
 * Return a list of all possible strings we could create.
 *
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 *
 * Input: S = "12345"
 * Output: ["12345"]
 *
 */
public class LeetCode784 {
    private Set<String> added = new HashSet<>();
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        if (S == null || S.length() == 0) {
            res.add("0");
            return res;
        }
        bracking(0, S, res);
        return res;
    }

    private void bracking(int index, String s, List<String> res) {
        if (index == s.length()) {
            return;
        }
        String newString = s;
        // upper
        if (s.charAt(index) >= 'a' && s.charAt(index) <= 'z') {
            newString = s.substring(0, index) + Character.toString(upperChar(s.charAt(index))) + s.substring(index + 1, s.length());
        }
        if (!added.contains(newString)) {
            added.add(newString);
            res.add(newString);
        }
        bracking(index + 1, newString, res);

        // lowest
        if (s.charAt(index) >= 'A' && s.charAt(index) <= 'Z') {
            newString = s.substring(0, index) + Character.toString(lowest(s.charAt(index))) + s.substring(index + 1, s.length());
        }
        if (!added.contains(newString)) {
            added.add(newString);
            res.add(newString);
        }
        bracking(index + 1, newString, res);


        if (!added.contains(s)) {
            added.add(s);
            res.add(s);
        }
        bracking(index + 1, s, res);
    }

    private char upperChar(char a) {
        return (char) ('A' + a - 'a');
    }

    private char lowest(char a) {
        return (char) ('a' + a - 'A');
    }

    public static void main(String[] args) {
        LeetCode784 leetCode784 = new LeetCode784();
        leetCode784.letterCasePermutation("a1b2");
        leetCode784.letterCasePermutation("3z4");
        leetCode784.letterCasePermutation("1234");
        leetCode784.letterCasePermutation("0");
        leetCode784.letterCasePermutation("C");
    }
}
