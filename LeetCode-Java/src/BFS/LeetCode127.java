package BFS;

import Link.leetcode.easy.ListNode;

import java.util.*;

/**
 * 127. 单词接龙 Word Ladder
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * <p>
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class LeetCode127 {
    /**
     * 时间超限：因为每个单词都是搜索 a ~ z 中可能
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthTOL(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.isEmpty()) {
            return 0;
        }
        Set<String> wordsSet = new HashSet<>(wordList);
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Set<String> visitedWord = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visitedWord.add(beginWord);
        int step = 0;
        while (!queue.isEmpty()) {
            int currentLevelCount = queue.size();
            step++;
            for (int l = 0; l < currentLevelCount; l++) {
                String currentWord = queue.poll();
                if (currentWord.equals(endWord)) {
                    return step;
                }
                List<String> expandStrings = expand(currentWord);
                for (String expendString: expandStrings) {
                    if (wordsSet.contains(expendString) && !visitedWord.contains(expendString)) {
                        queue.offer(expendString);
                        visitedWord.add(expendString);
                    }
                }
//                for (int i = 0; i < currentWord.length(); i++) {
//                    StringBuilder beginStringBuilder = new StringBuilder(currentWord);
//                    // 每个单词搜 a ~ z ： TOL
//                    for (char c = 'a'; c <= 'z'; c++) {
//                        String targetStr = beginStringBuilder.replace(i, i + 1, "" + c).toString();
//                        if (wordsSet.contains(targetStr) && !visitedWord.contains(targetStr)) {
//                            queue.offer(targetStr);
//                            visitedWord.add(targetStr);
//                        }
//                    }
//                }
            }
        }
        return 0;
    }

    private List<String> expand(String currentWord) {
        final int currentWordLength = currentWord.length();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < currentWordLength; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != currentWord.charAt(i)) {
                    res.add(currentWord.substring(0, i) + c + currentWord.substring(i + 1));
                }
            }
        }
        return res;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.isEmpty()) {
            return 0;
        }

        Map<String, List<String>> wordsSet = new HashMap<>();

        final int length = beginWord.length();
        /***
         * hot => *ot, h*t, ho*
         * begin: dot  end: acc
         * [aot，hot，cot, aoc, acc, ]
         *
         * 通过 * 进行连接映射
         * 比如，aot，hot，cot 被 映射到 *ot: [aot，hot，cot]
         * a*t: [aot], ao* [aot, aoc]
         *
         * begin：dot
         * 通过一次替换可以到达的点
         * *ot => [aot, hot, cot]
         * d*t => []
         * do* => []
         *
         * 通过两次可以达到的点：
         * aot:
         * *ot => ....
         * a*t => [aot]
         * ao* => [aot, aoc]
         *
         * hot:
         * *ot => ...
         * h*t => ...
         * ho* => ....
         *
         * cot:
         * ....
         *
         *
         */
        wordList.forEach(word -> {
            for (int i = 0; i < length; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, length);
                List<String> mappedStrings = wordsSet.getOrDefault(newWord, new LinkedList<>());
                mappedStrings.add(word);
                wordsSet.put(newWord,mappedStrings);
            }
        });

        Set<String> visitedWord = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visitedWord.add(beginWord);

        int step = 0;
        while (!queue.isEmpty()) {
            int currentLevelCount = queue.size();
            step++;
            for (int l = 0; l < currentLevelCount; l++) {
                String currentWord = queue.poll();
                if (currentWord.equals(endWord)) {
                    return step;
                }
                for (int i = 0; i < length; i++) {
                    ///
                    String findingWord = currentWord.substring(0, i) + "*" + currentWord.substring(i + 1, length);
                    List<String> mappedStrs = wordsSet.getOrDefault(findingWord, new LinkedList<>());
                    for (String str : mappedStrs) {
                        if (!visitedWord.contains(str)) {
                            queue.offer(str);
                            visitedWord.add(str);
                        }
                    }
                }
            }
        }
        return 0;
    }
    // TODO: 双向广度优先搜索

    public static void main(String[] args) {
        LeetCode127 leetCode127 = new LeetCode127();
        int step = leetCode127.ladderLengthTOL("hit", "cog", Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));// 5
        step = leetCode127.ladderLengthTOL("hit", "cog", Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log"}));  // 0
        step = leetCode127.ladderLengthTOL("hot", "dog", Arrays.asList(new String[]{"hot", "dog"}));               // 0
        step = leetCode127.ladderLengthTOL("leet", "code", Arrays.asList(new String[]{"lest", "leet", "lose", "code", "lode", "robe", "lost"}));//6
        System.out.println(step);
    }
}
