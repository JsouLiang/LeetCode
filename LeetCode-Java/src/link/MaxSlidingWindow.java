package link;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * link
 * Created by X-Liang
 * 2017-10-02-16:02
 *
 * @Description:
 */
public class MaxSlidingWindow {

    /**
     * 使用优先级队列解决，使用 Java 中的的最小堆
     * @param nums
     * @param k
     * @return
     */
    public int[] sloution1(int[] nums, int k) {
        if (k <= 0) { return new int[0]; }
        // java 中的优先级队列是最小堆，所以加入数据时要加入复数
        PriorityQueue<Integer> window = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            window.add(-nums[i]);
        }

        int[] result = new int[nums.length - k + 1];
        result[0] = -window.peek();

        for (int i = 1, j = k; j < nums.length; i++, j++) {
            window.remove(-nums[j - k]);
            window.add(-nums[j]);
            result[i] = -window.peek();
        }
        return result;
    }

    /**
     * 使用 Java 最大值优先队列
     * @param nums
     * @param k
     * @return
     */
    public int[] sloution2(int[] nums, int k) {
        if (k <= 0) { return new int[0]; }
        PriorityQueue window = new PriorityQueue<Integer>(k, Collections.reverseOrder());
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length - 1; i++) {
            if (i > k) {
                window.remove(nums[i - k]);
            }
            window.add(nums[i]);
            if (i >= k - 1) {
                result[i - k + 1] = (int)window.peek();
            }
        }
        return result;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        LinkedList<Integer> window = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            // 每当新数进来时，如果发现队列头部的数的下标，是窗口最左边数的下标，则扔掉
            if (!window.isEmpty() && window.peekFirst() <= i - k) {
                window.poll();
            }
            // 把队列尾部所有比新数小的都扔掉，保证队列是降序的
            while (!window.isEmpty() && nums[window.peekLast()] < nums[i]) {
                window.removeLast();
            }
            // 加入新数
            window.offerLast(i);
            // 队列头部就是该窗口内第一大的
            if ( i + 1 > k) {
                res[i + 1 - k] = nums[window.peek()];
            }
        }


        return res;
    }
}

class SlotionClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayToString(int[] nums, int length) {
        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return result.substring(0, result.length() - 2);
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            int[] ret = new MaxSlidingWindow().sloution1(nums, k);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}
