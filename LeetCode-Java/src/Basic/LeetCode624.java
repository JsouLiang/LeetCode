package Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode624 {
    // TODO:
    public int maxDistance(List<List<Integer>> arrays) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arrays.size(); i++) {
            for (int j = 0; j < arrays.get(i).size(); j++) {
                min = Math.min(arrays.get(i).get(j), min);
                max = Math.max(arrays.get(i).get(j), max);
            }
        }
        return max - min;
    }
    public static void main(String[] args) {
        LeetCode624 leetCode624 = new LeetCode624();
        List<List<Integer>> values = new ArrayList<>();
        values.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        values.add(new ArrayList<>(Arrays.asList(4, 5)));
        values.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        leetCode624.maxDistance(values);
    }
}
