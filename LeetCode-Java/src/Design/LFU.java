package Design;

import java.util.*;

/**
 * 每次淘汰使用次数最少的数据
 */
public class LFU<K, V> {

    final class Node implements Comparable<Node> {
        // 值
        V value;
        // 键
        K key;
        // 访问次数
        int count;
        // 访问时间
        long time;

        public Node(K key, V value, int count, long time) {
            this.value = value;
            this.key = key;
            this.count = count;
            this.time = time;
        }

        /**
         * Node 比较方法，在访问次数相同的情况下，比较访问时间
         */
        @Override
        public int compareTo(Node o) {
            int compare = Integer.compare(count, o.count);
            if (compare == 0) {
                return Long.compare(time, o.time);
            }
            return compare;
        }
    }

    private int minCount;

    private int capacity;

    private Map<K, Node> caches;

    private Map<Integer, List<Node>> nodeCountInfo;


    public LFU(int capacity) {
        this.capacity = capacity;
        this.caches = new HashMap<>();
        this.nodeCountInfo = new HashMap<>();
    }

    // 存储数据
    public void put(K key, V val) {
        Node node = caches.get(key);
        if (node == null) {
            // 新元素
            if (caches.size() >= capacity) {
                // 移除使用次数最少的元素
            }
            node = new Node(key, val, 1, System.nanoTime());
            caches.put(key, node);
            minCount = 1;
            // 出现次数为 1 的那一组中新加入一个 node
            List<Node> currentCountNodes = nodeCountInfo.getOrDefault(minCount, new ArrayList<>());
            currentCountNodes.add(node);
        } else {
            // 已近存在的元素
            node.value = val;
            nodeCountInfo.get(node.count).remove(node);

            node.count++;
            node.time = System.nanoTime();
            // 出现次数为 1 的那一组中新加入一个 node
            List<Node> currentCountNodes = nodeCountInfo.getOrDefault(node.count, new ArrayList<>());
            currentCountNodes.add(node);
        }
    }

    public V get(K key) {
        Node node = caches.get(key);
        if (node != null) {
            node.count++;
            nodeCountInfo.get(node.count).remove(node);
            node.time = System.nanoTime();
            // 出现次数为 1 的那一组中新加入一个 node
            List<Node> currentCountNodes = nodeCountInfo.getOrDefault(node.count, new ArrayList<>());
            currentCountNodes.add(node);
            return node.value;
        }
        return null;
    }
}

