package DFS;

import java.util.*;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * 根据题目描述，s 拆分后所有的部分必须都在 wordDict 中出现
 */
public class LeetCode139 {
    private Set<String> dict;
    Map<String, Boolean> visited;
    public boolean wordBreak(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        visited = new HashMap<>();
        boolean res = isContain(s);
        return true;
    }
    private boolean isContain(String s) {
        if (visited.get(s) != null) {
            return visited.get(s);
        }
        if (dict.contains(s)) {
            return true;
        }
        for (int split = 1; split < s.length(); split++) {
            String leftStr = s.substring(0, split);
            String rightStr = s.substring(split);
            boolean containLeft = isContain(leftStr);
            visited.putIfAbsent(leftStr, containLeft);
            boolean containRight = isContain(rightStr);
            visited.putIfAbsent(rightStr, containRight);
            if (containLeft && containRight) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode139 leetCode139 = new LeetCode139();
//        leetCode139.wordBreak("leetcode", new ArrayList<>(Arrays.asList("leet", "code")));
//        leetCode139.wordBreak("applepenapple", new ArrayList<>(Arrays.asList("apple", "pen")));
        leetCode139.wordBreak("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
