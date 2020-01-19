package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 * <p>
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class LeetCode131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> currentSplitStrs = new ArrayList<>();
        split(s, 0, currentSplitStrs, res);
        return res;
    }

    private void split(String s, int index, List<String> currentRes, List<List<String>> res) {
        if(s == null || s.length() == 0) {
            res.add(new ArrayList(currentRes));
            return;
        }
        /// 使用 subString 范围是 [0 ~ s.length]
        for (int i = 1; i <= s.length() - index; i++) {
            String currentString = s.substring(index, index + i);
            if (isPalindromeString(currentString)) {
                currentRes.add(currentString);
                String nextString = s.substring(i + index);
                split(nextString, 0, currentRes, res);
                currentRes.remove(currentRes.size() - 1);
            }
        }
    }

    private boolean isPalindromeString(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode131 leetCode131 = new LeetCode131();
//        leetCode131.partition("aab");
        leetCode131.partition("cbbbcc");
        String res =  "cbbbcc".substring(0, "cbbbcc".length());
        System.out.println(res);
    }
}
