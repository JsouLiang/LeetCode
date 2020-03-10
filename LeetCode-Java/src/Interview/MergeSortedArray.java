package Interview;

/**
 * 面试题 10.01. 合并排序的数组
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class MergeSortedArray {
    public void merge(int[] A, int m, int[] B, int n) {
        int aIndex = m - 1;
        int bIndex = n - 1;
        int index = A.length - 1;
        while (aIndex >= 0 && bIndex >= 0) {
            if (A[aIndex] > B[bIndex]) {
                A[index--] = A[aIndex--];
            } else {
                A[index--] = B[bIndex--];
            }
        }
        while (bIndex >= 0) {
            A[index--] = B[bIndex--];
        }
    }

    public static void main(String[] args) {
        MergeSortedArray mergeSortedArray = new MergeSortedArray();
        int[] A = new int[]{1,2,3,0,0,0};
        mergeSortedArray.merge(A, 3, new int[]{2, 5, 6}, 3);
    }
}
