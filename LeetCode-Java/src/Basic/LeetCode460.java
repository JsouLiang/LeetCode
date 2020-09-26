package Basic;

import Design.LFU;

public class LeetCode460 {
    LFU<Integer, Integer> lfu;
    public LeetCode460(int capacity) {
        lfu = new LFU<>(capacity);
    }

    public int get(int key) {
        Integer val = lfu.get(key);
        if (val == null) {
            return -1;
        }
        return val;
    }

    public void put(int key, int value) {
        lfu.put(key,value);
    }

    public static void main(String[] args) {
        LeetCode460 cache = new LeetCode460(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        cache.get(2);       // 返回 -1 (未找到key 2)
        cache.get(3);       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        cache.get(1);       // 返回 -1 (未找到 key 1)
        cache.get(3);       // 返回 3
        cache.get(4);       // 返回 4

    }
}
