package Basic.BinarySearch;

/**
 *
 * 给定 n 本书, 第 i 本书的页数为 pages[i]. 现在有 k 个人来复印这些书籍, 而每个人只能复印编号连续的一段的书,
 * 比如一个人可以复印 pages[0], pages[1], pages[2],
 * 但是不可以只复印 pages[0], pages[2], pages[3] 而不复印 pages[1].
 *
 * 所有人复印的速度是一样的, 复印一页需要花费一分钟, 并且所有人同时开始复印.
 * 怎样分配这 k 个人的任务, 使得这 n 本书能够被尽快复印完?
 *
 * 返回完成复印任务最少需要的分钟数.
 *
 * 样例 1:
 *
 * 输入: pages = [3, 2, 4], k = 2
 * 输出: 5
 * 解释: 第一个人复印前两本书, 耗时 5 分钟. 第二个人复印第三本书, 耗时 4 分钟.
 * 样例 2:
 *
 * 输入: pages = [3, 2, 4], k = 3
 * 输出: 4
 * 解释: 三个人各复印一本书.
 */
public class CopyBooks {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0 || k == 0) {
            return 0;
        }
        int minTime = Integer.MIN_VALUE;
        int maxTime = 0;
        for (int i = 0; i < pages.length; i++) {
            minTime = Math.max(pages[i], minTime);
            maxTime += pages[i];
        }

        while (minTime + 1 < maxTime) {
            int middle = minTime + (maxTime - minTime) / 2;
            int personNum = calculPersonNumsOfTime(pages, middle);
            // 如果需要的人数 大于 k，则说明每个人middle 的耗时是不够的
            if (personNum > k) {
               minTime = middle;
            } else if (personNum < k) { // 如果需要的人数 小于 k，则说明每个人middle 的耗时够的
                maxTime = middle;
            } else {
                maxTime = middle;
            }
        }
        if (calculPersonNumsOfTime(pages, maxTime) == k) {
            return maxTime;
        }
        if (calculPersonNumsOfTime(pages, minTime) == k) {
            return minTime;
        }
        return -1;
    }

    /**
     * 最长 time 的时间，需要多少个人力
     * [3, 2, 4, 3, 3]  time: 5
     */
    private int calculPersonNumsOfTime(int[] pages, int time) {
        int personCount = 1;
        int currentTime = 0;
        for (int i = 0; i < pages.length; i++) {
            if (currentTime + pages[i] <= time) {
                currentTime += pages[i];
                if (i == pages.length - 1) {
                    personCount++;
                }
            } else {
                personCount++;
                currentTime = 0;
            }
        }
        return personCount;
    }

    public static void main(String[] args) {
        CopyBooks copyBooks = new CopyBooks();
        int res =  copyBooks.copyBooks(new int[]{3, 2, 4}, 2);
        res =  copyBooks.copyBooks(new int[]{3, 2, 4}, 3);
    }
}
