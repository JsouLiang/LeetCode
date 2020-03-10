package Basic.string;

public class LeetCode243 {
    public int shortestDistance(String[] words, String word1, String word2) {
        /// word1和 word2出现的位置
        int word1Index = -1, word2Index = -1;
        /// word1和 word2位置距离的最小值
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                word1Index = i;
            } else if (words[i].equals(word2)) {
                word2Index = i;
            }
            /// 如果找到 word1 和 word2，看看他们的距离是否小于当前的最短距离
            /// 如果找到一个 word1，那么所有的 word2 的位置都会与之做距离计算
            if (word1Index != -1 && word2Index != -1) {
                minDistance = Math.min(minDistance, Math.abs(word1Index - word2Index));
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        LeetCode243 leetCode243 = new LeetCode243();
        int res = leetCode243.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice");
        res = leetCode243.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "makes");
//        leetCode243.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice");

    }
}
