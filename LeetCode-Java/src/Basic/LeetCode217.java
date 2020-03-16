package Basic;

import java.util.HashSet;
import java.util.Set;

/**
 * 217. 存在重复元素
 */
public class LeetCode217 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (Integer num: nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
