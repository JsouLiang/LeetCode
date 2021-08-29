package DoublePoint;

import DFS.LeetCode979;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 881. 救生艇
 * 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
 *
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 *
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/boats-to-save-people
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode881 {

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        boolean[] hasSaved = new boolean[people.length];
        List<List<Integer>> res = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < people.length; i++) {
            if (hasSaved[i]) {
                continue;
            }
            int firstPeople = people[i];
            boolean isTwoPeople = false;
            for (int j = people.length - 1; j > i; j--) {
                if (hasSaved[j]) {
                    continue;
                }
                // 2 people
                if (firstPeople + people[j] <= limit) {
                    hasSaved[i] = hasSaved[j] = true;
                    List<Integer> r = new ArrayList<>();
                    r.add(i); r.add(j);
                    res.add(r);
                    count++;
                    isTwoPeople = true;
                    break;
                }
            }
            if (!isTwoPeople) {
                hasSaved[i] = true;
                count++;
                List<Integer> r = new ArrayList<>();
                r.add(i);
                res.add(r);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode881 leetCode881 = new LeetCode881();
        leetCode881.numRescueBoats(new int[]{3,5,3,4}, 5);
    }
}
