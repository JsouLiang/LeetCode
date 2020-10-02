package Basic;

import java.util.HashSet;
import java.util.Set;

public class LeetCode771 {
    public int numJewelsInStones(String J, String S) {
        Set<Character> jewelSet = new HashSet<>();
        char[] jewels = J.toCharArray();
        for (char c : jewels) {
            jewelSet.add(c);
        }

        char[] stones = S.toCharArray();
        int count = 0;
        for (char c : stones) {
            if (jewelSet.contains(c)) {
                count++;
            }
        }
        return count;
    }
}
