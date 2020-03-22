package Basic;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 最小的 K 个数
 */
public class GetLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num: arr) {
            queue.add(num);
        }
        int[] res = new int[k];
        while (k > 0) {
            res[--k] = queue.poll();
        }
        return res;
    }

    public int[] getLeastNumbersDivide(int[] arr, int k) {
        int[] res = divideRes(arr, 0, arr.length - 1, k);
        return res;
    }


    private int[] divideRes(int[] arr, int left, int right, int k) {
        if (right - left <= k - 1) {
            return subArrayOf(arr, left, right);
        }
        int middle = left + (right - left) / 2;
        // [left, middle]
        int[] leftLeastNums = divideRes(arr, left, middle, k);
        // [middle + 1, right]
        int[] rightLeastNums = divideRes(arr, middle + 1, right, k);
        // Conquer
        int[] res = marge(leftLeastNums, rightLeastNums, k);
        return res;
    }

    private int[] marge(int[] leftLeastNums, int[] rightLeastNums, int k) {
        int[] res = new int[k];
        int lft = 0, rit = 0, resi = 0;
        while (lft < leftLeastNums.length && rit < rightLeastNums.length && resi < k) {
            if (leftLeastNums[lft] < rightLeastNums[rit]) {
                res[resi++] = leftLeastNums[lft++];
            } else {
                res[resi++] = rightLeastNums[rit++];
            }
        }
        while (resi < k && lft < leftLeastNums.length) {
            res[resi++] = leftLeastNums[lft++];
        }
        while (resi < k && rit < rightLeastNums.length) {
            res[resi++] = rightLeastNums[rit++];
        }
        return res;
    }

    private int[] subArrayOf(int[] arr, int left, int right) {
        int[] res = new int[right - left + 1];
        for (int index = 0, i = left; i <= right; i++) {
            res[index++] = arr[i];
        }
        Arrays.sort(res);
        return res;
    }

    public static void main(String[] args) {
        GetLeastNumbers getLeastNumbers = new GetLeastNumbers();
        getLeastNumbers.getLeastNumbersDivide(new int[]{3, 2, 1}, 2);
        getLeastNumbers.getLeastNumbersDivide(new int[]{0, 1, 2, 1}, 1);
    }
}
