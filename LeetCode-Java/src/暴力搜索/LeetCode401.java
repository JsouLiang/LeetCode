package 暴力搜索;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 401. Binary Watch
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *https://blog.csdn.net/u014248127/article/details/53844748
 */
public class LeetCode401 {
    private List<Integer> hour = Arrays.asList(
//            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"
            1, 2, 4, 8
    );
    private List<Integer> minutes = Arrays.asList(
            1, 2, 4, 8, 16, 32
//            "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
//            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
//            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
//            "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
//            "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
//            "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"
    );
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        if (num == 0) {
            return res;
        }
        return solution(num);
    }

    private List<String> solution(int num) {
        if (num == 0) {
            return new ArrayList<>();
        }
        for (int i = 0; i <= num; i++) {
            List<List<Integer>> hourNums = new ArrayList<>();
            selectNumbers(i, hour, new ArrayList<>(), hourNums);
            List<List<Integer>> minuteNums = new ArrayList<>();
            selectNumbers(num - i,  minutes, new ArrayList<>(), minuteNums);
        }
        return null;
    }

    private void selectNumbers(int count, List<Integer> target, List<Integer> selectedNum, List<List<Integer>> res) {
        if (selectedNum.size() == count) {
            res.add(selectedNum);
            return;
        }

        for (int i = 0; i < target.size(); i++) {
            Integer value = target.get(i);
            selectedNum.add(value);
            selectNumbers(count, target,selectedNum, res);
            selectedNum.remove(res.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode401 leetCode401 = new LeetCode401();
        leetCode401.readBinaryWatch(2);
    }
}
