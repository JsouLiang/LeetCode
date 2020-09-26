package DynamicProgramming.leetcode;

public class LeetCode72 {
    private String word1;
    private String word2;
    Integer[][] visited;
    public int minDistance(String word1, String word2) {
        visited = new Integer[word1.length()][word2.length()];
        return dp(word1.length() - 1, word2.length() - 1);
    }

    private int dp(int word1Index, int word2Index) {
        if (word1Index == -1) {
            /// word1 结束，word2 还剩 Word2Index + 1 个字符
            return word2Index + 1;
        }
        if (word2Index == -1) {
            return word1Index + 1;
        }
        if (visited[word1Index][word2Index] != null) {
            return visited[word1Index][word2Index];
        }
        if (word1.charAt(word1Index) == word2.charAt(word2Index)) {
            return dp(word1Index - 1, word2Index - 1);
        }
        // word1 在 word1Index 位置插入一个元素，此时 word2Index 前移，word1Index 不动；操作数 + 1
        int insert = dp(word1Index, word2Index - 1) + 1;
        int delete = dp(word1Index - 1, word2Index) + 1;
        int replace = dp(word1Index - 1, word2Index - 1) + 1;
        int res = Math.min(Math.min(insert, delete), replace);
        visited[word1Index][word2Index] = res;
        return res;
    }
}
