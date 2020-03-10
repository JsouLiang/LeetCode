package Basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode202 {
    private Set<Integer> visited = new HashSet<>();
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        boolean res = _isHappyHelper(n);
        return res;
    }

    private boolean _isHappyHelper(int n) {
        if (n == 1) {
            return true;
        }
        int[] numbers = nums(n);
        int currentRes = 0;
        for (int i = 0; i < numbers.length; i++) {
            currentRes += numbers[i] * numbers[i];
        }
        if (visited.contains(currentRes)) {
            return false;
        }
        visited.add(currentRes);
        return _isHappyHelper(currentRes);
    }

    private int[] nums(int n) {
        List<Integer> res = new ArrayList<>();
        while (n != 0) {
            res.add(n % 10);
            n /= 10;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        LeetCode202 leetCode202 = new LeetCode202();
        leetCode202.isHappy(2);
//        leetCode202.isHappy(19);
    }
}
