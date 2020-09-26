package Basic.BinarySearch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode378 {
    class Node {
        int index;
        int value;
        Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix.length == 0) {
            return 0;
        }
        /// 当前选则第 index 行的第 columnIndex[index] 个元素
        int[] columnIndex = new int[matrix.length];
        PriorityQueue<Node> minQueue = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
        int res = 0;
        int[] ress = new int[k];
        for (int column = 0; column < matrix.length; column++) {
            if (columnIndex[column] >= 0) {
                minQueue.add(new Node(column, matrix[column][columnIndex[column]]));
            }
        }
        for (int i = 0; i < k; i++) {
            Node currentMinNode = minQueue.poll();
            columnIndex[currentMinNode.index]++;
            if (columnIndex[currentMinNode.index] < matrix.length) {
                minQueue.add(new Node(currentMinNode.index, matrix[currentMinNode.index][columnIndex[currentMinNode.index]]));
            }
            res = currentMinNode.value;
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode378 leetCode378 = new LeetCode378();
        leetCode378.kthSmallest(new int[][] {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8);
    }
}
