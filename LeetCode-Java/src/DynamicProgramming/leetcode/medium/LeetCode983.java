package DynamicProgramming.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

public class LeetCode983 {
    Set<Integer> travelDays;
    Integer[] memoryInfos;
    int[] costs;
    public int mincostTickets(int[] days, int[] costs) {
        this.costs = costs;
        travelDays = new HashSet<>();
        for (int day: days) {
            travelDays.add(day);
        }
        memoryInfos = new Integer[365];
//        return dpBackward(1);
        int res = dpForward(days[days.length - 1]);
        return res;
    }

    private int dpBackward(int day) {
        if (day > 365) {
            return 0;
        }
        if (memoryInfos[day] != null) {
            return memoryInfos[day];
        }
        if (travelDays.contains(day)) {
            int minVal1 = Math.min(dpBackward(day + 1) + costs[0], dpBackward(day + 7) + costs[1]);
            memoryInfos[day] = Math.min(minVal1, dpBackward(day + 30) + costs[2]);
        } else {
            memoryInfos[day] = dpBackward(day + 1);
        }
        return memoryInfos[day];
    }

    private int dpForward(int endDay) {
        int[] dp = new int[endDay + 1];
        /// 还没开始旅行，花费为 0
        dp[0] = 0;
        for (int day = 1; day <= endDay; day++) {
            if (travelDays.contains(day)) {
                /// day - 7, day - 30 < 0 意味着我们可以从第一天到今天只购买一个 7 天票，或者 30 天票，此时的花费只有 costs[1] 或者 costs[2]
                int one = dp[day - 1] + costs[0];
                int seven = dp[Math.max(0, day - 7)] + costs[1];
                int thirty = dp[Math.max(0, day - 30)] + costs[2];
                dp[day] = Math.min(Math.min(one, seven), thirty);

            } else {
                dp[day] = dp[day - 1];
            }
        }
        return dp[endDay];
    }

    public static void main(String[] args) {
        LeetCode983 leetcode983 = new LeetCode983();
        leetcode983.mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{7, 2, 15});
    }
}
