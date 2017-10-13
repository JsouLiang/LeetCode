package link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * link
 * Created by X-Liang
 * 2017-10-02-08:36
 *
 * @Description:
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;

        ArrayList<Integer> originalNums = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            originalNums.add(nums[i]);
        }


        List<List<Integer>> outter = new ArrayList<>();
        if (length < 3) {
            return outter;
        }

        for (int i = 0; i < length-1; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                int sum = nums[i] + nums[j];
                List<Integer> subList = originalNums.subList(j + 1, length);
                // 不包含 三个数相加为0 的数
                if (!subList.contains(-sum)) {
                    continue;
                }

                ArrayList<Integer> result = new ArrayList<>();
                result.add(nums[i]);
                result.add(nums[j]);
                result.add(-sum);

                // 判断当前的结果是否在结果集数组中存在
                int flag = 0;
                for (List<Integer> list: outter) {
                    if (result.containsAll(list)) {
                        flag = 1;
                    }
                }

                if (flag == 0) {
                    outter.add(result);
                }
            }
        }
        return outter;
    }
}
