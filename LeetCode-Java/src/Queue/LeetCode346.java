package Queue;

import Basic.BinarySearch.LeetCode34;
import Link.leetcode.medium.LeetCode2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode346 {
    double sum;
    Queue<Integer> queue;
    int size = 0;
    /**
     * Initialize your data structure here. */
    public LeetCode346(int size) {
        queue = new LinkedList<>();
        sum = 0;
        this.size = size;
    }

    public double next(int val) {
        queue.offer(val);
        sum += val;
        double res = 0.0;
        if (queue.size() <= size) {
            res = sum / queue.size();
        } else {
            sum -= queue.poll();
            res = sum / size;
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode346 leetCode346 = new LeetCode346(3);
        double res = leetCode346.next(1);
        res = leetCode346.next(10);
        res = leetCode346.next(3);
        res = leetCode346.next(5);
    }
}
