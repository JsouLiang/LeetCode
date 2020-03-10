package Queue.priorityQueue;

import Basic.BinarySearch.LeetCode34;

import java.util.*;

/**
 * 347. Top K Frequent Elements
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 */
public class LeetCode347 {
    class HeapNode {
        private int value;
        int count;
        public HeapNode(int value) {
            this.value = value;
            this.count = 0;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0 || k == 0) {
            return res;
        }
        Map<Integer, HeapNode> mapping = new HashMap<>();

        /// 最小堆：堆顶存放出现次数最多的前 K 个元素中出现次数最小的那个
        PriorityQueue<HeapNode> minimumQueue = new PriorityQueue<>(k, new Comparator<HeapNode>() {
            @Override
            public int compare(HeapNode o1, HeapNode o2) {
                return o1.count - o2.count;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            final int value = nums[i];
            HeapNode node = mapping.getOrDefault(value, new HeapNode(value));
            node.count++;
            mapping.putIfAbsent(value, node);
        }

        for (HeapNode node : mapping.values()) {
            if (minimumQueue.size() < k) {
                minimumQueue.add(node);
                continue;
            }
            if (node.count > minimumQueue.peek().count) {
                minimumQueue.poll();
                minimumQueue.add(node);
            }
        }

        for (int i = 0; i < k; i++) {
            res.add(0, minimumQueue.remove().value);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode347 leetCode347 = new LeetCode347();
        leetCode347.topKFrequent(new int[]{1,1,1,2,2,3}, 2);
        leetCode347.topKFrequent(new int[]{1}, 1);
        // [4,-1,2,-5,-8,8,0]
        leetCode347.topKFrequent(new int[] {5,1,-1,-8,-7,8,-5,0,1,10,8,0,-4,3,-1,-1,4,-5,4,-3,0,2,2,2,4,-2,-4,8,-7,-7,2,-8,0,-8,10,8,-8,-2,-9,4,-7,6,6,-1,4,2,8,-3,5,-9,-3,6,-8,-5,5,10,2,-5,-1,-5,1,-3,7,0,8,-2,-3,-1,-5,4,7,-9,0,2,10,4,4,-4,-1,-1,6,-8,-9,-1,9,-9,3,5,1,6,-1,-2,4,2,4,-6,4,4,5,-5}, 7);

    }
}
