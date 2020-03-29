package Basic;

import java.util.HashMap;
import java.util.Map;

public class LeetCode914 {

    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }

    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int num : deck) {
            Integer count = mapping.get(num);
            if (count == null) {
                count = 0;
            }
            mapping.put(num, count+1);
        }

        Integer cun = null;
        for (Integer key : mapping.keySet()) {
            if (cun == null) {
                cun = mapping.get(key);
            } else {
                Integer currentCount = mapping.get(key);
                Integer gcd = gcd(cun, currentCount);
                if (gcd == 1) {
                    return false;
                }
                cun = gcd;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        LeetCode914 leetCode914 = new LeetCode914();
        leetCode914.hasGroupsSizeX(new int[]{1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3});

        int v = leetCode914.gcd(3, 5);
        System.out.println(v);
    }
}
