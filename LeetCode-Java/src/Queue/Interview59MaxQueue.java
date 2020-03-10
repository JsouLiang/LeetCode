package Queue;

import java.util.*;

class MaxQueue {
    Queue<Integer> queue;
    Deque<Integer> decreaseQueue;
    public MaxQueue() {
        queue = new LinkedList<>();
        decreaseQueue = new LinkedList<>();
    }


    public int max_value() {
        if (decreaseQueue.isEmpty()) {
            return -1;
        }
        return decreaseQueue.peekFirst();
    }

    public void push_back(int value) {
        /// 如果当前 value > 前面出现的元素，因为前面出现的元素肯定先于 value 出队，所以只要有 value 在
        /// 前面比 value 小的元素肯定不会是 max_value
        while (!decreaseQueue.isEmpty() && decreaseQueue.peekLast() < value) {
            decreaseQueue.pollLast();
        }
        queue.offer(value);
        decreaseQueue.add(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int front = queue.poll();
        /// 如果当前出队的恰好是当前最大值，那么最大值应该从 decreaseQueue 中出队
        if (front == decreaseQueue.peekFirst()) {
            decreaseQueue.pollFirst();
        }
        return front;
    }
}

public class Interview59MaxQueue {
    Queue<Integer> queue;
    PriorityQueue<Integer> maxQueue;

    public Interview59MaxQueue() {
        queue = new ArrayDeque<>();
        maxQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
    }

    public int max_value() {
        if (maxQueue.isEmpty()) {
            return -1;
        }
        return maxQueue.peek();
    }

    public void push_back(int value) {
        queue.offer(value);
        maxQueue.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int front = queue.poll();
        maxQueue.remove(front);
        return front;
    }

    public static void main(String[] args) {
        Interview59MaxQueue maxQueue = new Interview59MaxQueue();
        int valu1 = maxQueue.max_value();
        maxQueue.push_back(3);
        maxQueue.push_back(1);
        maxQueue.push_back(1);
        maxQueue.pop_front();
        maxQueue.max_value();
    }
}
