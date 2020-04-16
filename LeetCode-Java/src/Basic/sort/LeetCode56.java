package Basic.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 */
public class LeetCode56 {
    class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 1) {
            return intervals;
        }
        List<Interval> intervalList = new ArrayList<>();
        for (int[] interval : intervals) {
            intervalList.add(new Interval(interval[0], interval[1]));
        }
        intervalList.sort((o1, o2) -> {
            int startR = o1.start - o2.start;
            if (startR == 0) {
                return o1.end - o2.end;
            }
            return startR;
        });
        List<Interval> res = new ArrayList<>();
        Interval currentMargeInterval = intervalList.get(0);
        Interval currentInterval = null;
        for (int index = 1; index < intervalList.size(); index++) {
            currentInterval = intervalList.get(index);
            if (currentInterval.end <= currentMargeInterval.end) {
              continue;
            } else if (currentInterval.start <= currentMargeInterval.end) {
                currentMargeInterval.end = currentInterval.end;
            } else {
                res.add(currentMargeInterval);
                currentMargeInterval = currentInterval;
            }
        }
        res.add(currentMargeInterval);
        int[][] resInt = new int[res.size()][2];
        int index = 0;
        for (Interval interval: res) {
            resInt[index++] = new int[]{interval.start, interval.end};
        }
        return resInt;
    }

    public static void main(String[] args) {
        LeetCode56 leetCode56 = new LeetCode56();
        leetCode56.merge(new int[][]{
                {1, 4,},
                {4, 5,},
                {4, 6},
                {2, 5},
                {3, 9}
        });
    }
}
