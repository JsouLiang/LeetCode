package 暴力搜索;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 401. Binary Watch
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *
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
        for (int i = 0; i < num; i++) {
            List<Integer> hourNums = new ArrayList<>();
            selectNumbers(i, 0, hour, hourNums);
            List<Integer> minuteNums = new ArrayList<>();
            selectNumbers(num - i, 0, minutes, minuteNums);
        }
        return null;
    }

    private List<Integer> selectNumbers(int count, int index, List<Integer> target, List<Integer> res) {
        if (index == count) {
            return res;
        }
        for (int i = 0; i < target.size(); i++) {
            Integer value = target.get(i);
            res.add(value);
            selectNumbers(count, index+1, target, res);
            res.remove(res.size() - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode401 leetCode401 = new LeetCode401();
        leetCode401.readBinaryWatch(2);
    }
}
