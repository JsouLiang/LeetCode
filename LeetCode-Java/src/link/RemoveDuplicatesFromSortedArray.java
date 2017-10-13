package link;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * link
 * Created by X-Liang
 * 2017-10-02-12:38
 *
 * @Description:
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        // 通过 TreeSet 去重
        Set<Integer> nums_set = new TreeSet<>();
        for (int i: nums) {
            nums_set.add(nums[i]);
        }
        // 题目要获得 list 长度
        int length = nums_set.size();

        // 要交非重复的元素放到 0 ~ length
        List<Integer> set_list = new ArrayList<>(nums_set);
        for (int i = 0; i < length; i++) {
            nums[i] = set_list.get(i);
        }

        return length;
    }
}
