package 排序算法;

/**
 * 排序算法
 * Created by X-Liang
 * 2017-12-04-23:29
 *
 * @Description:   归并排序
 */
public class MergerSort {
    public int[] sort(int[] nums) {
        int[] result = sortList(nums, 0, nums.length);
        return result;
    }

    /**
     * 分治排序数组
     * 将数组不断地左右一半一半地分治
     * left与 right 组成数组的半开半闭区间 [left, right)
     * @param data  待排序数组
     * @param left  数组起点
     * @param right 数组终点
     * @return 排序完成后的数组
     */
    private int[] sortList(int[] data, int left, int right) {
        if (right - left == 1) {
            return new int[]{data[left]};
        }
        int middle = left + (right - left) / 2;
        int[] sortedLeftList = sortList(data, left, middle);
        int[] sortedRightList = sortList(data, middle, right);
        return merge(sortedLeftList, sortedRightList);
    }

    /**
     * 将 left 和 right 两个排序完成后的数组进行合并
     * left right 是排序完成后的数组
     * @param left  排序完成后的数组
     * @param right 排序完成后的数组
     * @return left + right 合并后的数组
     */
    private int[] merge(int[] left, int[] right) {
        // 当有一边的数组长度为0时，返回另一边
        if (left.length == 0) {
            return  right;
        } else if (right.length == 0) {
            return left;
        }

        int[] result = new int[left.length + right.length];
        int leftLength = left.length; int rightLength = right.length;
        int leftPoint = 0;          // 左边数组的 index
        int rightPoint = 0;         // 右边数组的 index
        int resultPoint = 0;        // 结果数组的 index

        while (leftPoint < leftLength && rightPoint < rightLength) {
            if (left[leftPoint] < right[rightPoint]) {
                result[resultPoint++] = left[leftPoint++];
            } else {
                result[resultPoint++] = right[rightPoint++];
            }
        }

        // 如果左边的数组没有遍历完成
        while (leftPoint < leftLength) {
            result[resultPoint++] = left[leftPoint++];
        }

        // 右边的数组没有遍历完成
        while (rightPoint < rightLength) {
            result[resultPoint++] = right[rightPoint++];
        }

        return result;

    }

}
