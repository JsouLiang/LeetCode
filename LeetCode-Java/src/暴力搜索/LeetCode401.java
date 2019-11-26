package 暴力搜索;

import java.util.*;

/**
 * 401. Binary Watch
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *https://blog.csdn.net/u014248127/article/details/53844748
 */
public class LeetCode401 {
    private List<Integer> hour = Arrays.asList(
            1, 2, 4, 8
    );
    private List<Integer> minutes = Arrays.asList(
            1, 2, 4, 8, 16, 32
    );

    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        if (num == 0) {
            res.add("0:00");
            return res;
        }
        return solution(num);
    }

    private List<String> solution(int num) {
        if (num == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i <= num; i++) {
            List<List<Integer>> hourNums = new ArrayList<>();
            visisted.clear();
            /// hours 列亮了 i 个
            /// 共有 hourNums 中组合形式
            selectNumbers(i, 0, hour, new ArrayList<>(), hourNums, true);

            List<List<Integer>> minuteNums = new ArrayList<>();
            visisted.clear();
            /// minutes 列亮了 num - i 个点
            /// 共有 minuteNums 中组合形式
            selectNumbers(num - i, 0,  minutes, new ArrayList<>(), minuteNums, false);
            for (int h = 0; h < hourNums.size(); h++) {
                for (int m = 0; m < minuteNums.size(); m++) {
                    int hour = sumOfList(hourNums.get(h));
                    int minute = sumOfList(minuteNums.get(m));
                    String hourStr = String.valueOf(hour);
                    String minuteStr = String.valueOf(minute);
                    if (minute < 10) {
                        minuteStr = "0" + minuteStr;
                    }
                    res.add(hourStr + ':' + minuteStr);
                }
            }
        }
        return res;
    }
    private Set<Integer> visisted = new HashSet<>();
    private void selectNumbers(int count, int currentInde, List<Integer> target, List<Integer> selectedNum, List<List<Integer>> res, boolean isHour) {
        int sumValue = sumOfList(selectedNum);

        if (isHour && sumValue > 11) {
            return;
        }
        if (!isHour && sumValue > 59) {
            return;
        }

        if (selectedNum.size() == count) {
            if (visisted.contains(sumValue)) {
                return;
            }
            visisted.add(sumValue);
            res.add(new ArrayList<>(selectedNum));
            return;
        }

        for (int i = currentInde; i < target.size(); i++) {
            Integer value = target.get(i);
            selectedNum.add(value);
            int sumOfValue = sumOfList(selectedNum);
            selectNumbers(count, i + 1, target, selectedNum, res, isHour);
            selectedNum.remove(selectedNum.size() - 1);
        }
    }

    private Integer sumOfList(List<Integer> list) {
        return list.stream().reduce(0, (sum, item)-> {
            sum+=item;
            return sum;
        });
    }

    public static void main(String[] args) {
        LeetCode401 leetCode401 = new LeetCode401();
//        leetCode401.readBinaryWatch(1);
        /**
         * ["0:03",
         * "0:05",
         * "0:06",
         * "0:09",
         * "0:10",
         * "0:12",
         * "0:17",
         * "0:18",
         * "0:20",
         * "0:24",
         * "0:33",
         * "0:34",
         * "0:36",
         * "0:40",
         * "0:48",
         * "1:01",
         * "1:02",
         * "1:04",
         * "1:08",
         * "1:16",
         * "1:32",
         * "2:01",
         * "2:02",
         * "2:04",
         * "2:08",
         * "2:16",
         * "2:32",
         * "3:00",
         * "4:01",
         * "4:02",
         * "4:04",
         * "4:08",
         * "4:16",
         * "4:32",
         * "5:00",
         * "6:00",
         * "8:01",
         * "8:02",
         * "8:04",
         * "8:08",
         * "8:16",
         * "8:32",
         * "9:00",
         * "10:00"
         * ]
         */
        leetCode401.readBinaryWatch(2);
    }
}
