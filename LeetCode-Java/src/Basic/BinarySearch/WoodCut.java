package Basic.BinarySearch;

/**
 * **描述**
 * 有一些原木，现在想把这些木头切割成一些**长度相同的小段木头**，
 * 需要得到的小段的数目至少为k。当然，我们希望得到的小段越长越好，你需要计算能够得到的小段木头的最大长度。
 * 木头长度的单位是厘米。原木的长度都是正整数，我们要求切割得到的小段木头的长度也要求是整数。
 * 无法切出要求至少 *k* 段的,则返回 0 即可。
 * ::将 L 块大木头，分成至少 K 块小木头::
 * ```
 * 输入:
 * L = [232, 124, 456]
 * k = 7
 * 输出: 114
 * Explanation: 我们可以把它分成114cm的7段，而115cm不可以
 * ```
 *
 * ```
 * 输入:
 * L = [1, 2, 3]
 * k = 7
 * 输出: 0
 * 说明:很显然我们不能按照题目要求完成。
 * ```
 */
public class WoodCut {
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0) {
            return 0;
        }
        int maxBounds = -1;
        for (int i = 0; i < L.length; i++) {
            maxBounds = Math.max(maxBounds, L[i]);
        }
        int minBound = 1;
        while (minBound + 1 < maxBounds) {
            int middle = minBound + (maxBounds - minBound) / 2;
            int cutCount = cutCount(middle, L);
            if (cutCount >= k) {
                minBound = middle;
            } else {
                maxBounds = middle;
            }
        }
        if (cutCount(maxBounds, L) >= k) {
            return maxBounds;
        }
        if (cutCount(minBound, L) >= k) {
            return minBound;
        }
        return 0;
    }

    private int cutCount(int length, int[] L) {
        int nums = 0;
        for (int i = 0; i < L.length; i++) {
            nums += L[i] / length;
        }
        return nums;
    }

    public static void main(String[] args) {
        WoodCut woodCut = new WoodCut();
        int res = woodCut.woodCut(new int[]{232, 124, 456}, 7);
        res = woodCut.woodCut(new int[]{1, 2, 3}, 7);
        res = woodCut.woodCut(new int[]{2147483644,2147483645,2147483646,2147483647}, 4);

        System.out.println(res);
    }
}
