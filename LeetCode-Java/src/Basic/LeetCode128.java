package Basic;

import java.util.HashSet;
import java.util.Set;

public class LeetCode128 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num :
                nums) {
            set.add(num);
        }

        int length = 0;
        for (int num : nums) {
            if (!set.contains(num)) {
                continue;
            }
            int currentLen = 0;
            /// less than num
            int less = num;
            while (set.contains(less)) {
                set.remove(less);
                currentLen++;
                less--;
            }
            /// bigger than num
            int bigger = num + 1;
            while (set.contains(bigger)) {
                set.remove(bigger);
                currentLen++;
                bigger++;
            }
            length = Math.max(currentLen, length);
        }
        return length;
    }
}
