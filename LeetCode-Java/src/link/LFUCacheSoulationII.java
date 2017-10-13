package link;

import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * link
 * Created by X-Liang
 * 2017-10-03-13:31
 *
 * @Description:
 *
 * LFU 缓存策略
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item.
 * For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency),
 * the least recently used key would be evicted.
 *
 */
/**
 * LFUCache 实现第二种方式
 */
public class LFUCacheSoulationII {
    // cache 存储时对应的 key - value
    HashMap<Integer, Integer> keyValues;
    // 每个 key 对应的 cache 访问次数
    // key: cache 对应的 key
    // value: key 对应的 cache 访问次数
    HashMap<Integer, Integer> keyCount;
    // 通过访问次数获取 key，比如获取访问 1次的 key countKeySets[1] 可以获取所有访问过1次的缓存
    HashMap<Integer, LinkedHashSet<Integer>> countKeySets;
    // 缓存的容量
    int capacity;
    // 计算当前的最小使用次数
    int min;

    public LFUCacheSoulationII(int capacity) {
        this.capacity = capacity;
        min = -1;
        keyValues = new HashMap<>();
        keyCount = new HashMap<>();
        countKeySets = new HashMap<>();
        countKeySets.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        // 并不存在 key
        if (!keyValues.containsKey(key)) {
            return -1;
        }
        // 获取 key 对应的访问次数
        int count = keyCount.get(key);
        keyCount.put(key, count+1); // key 对应的访问次数++

        countKeySets.get(count).remove(key);  // 将之前的 count 对应的 key 集合中删除该 key

        // 假设 当前 key = 1 value = 1，count = min = 1，如果 我这里 get 的是1，那么 key = 1 的 count = 2，
        // 访问次数为1对应的 key 数组中没有数据，这时最小访问次数要 ++
        // 因为只要一往 cache 中put 数据，min 就会重新变为1，如果一直 get数据，那 min 也必须随着变化
        // 变化的条件就是当前 get 的数据是“最小访问次数”数组中的数，并且访问完这个数之后，没有这个访问次数的数据了，那么 min 要++
        if (count == min && countKeySets.get(count).size() == 0) {
            min++;
        }

        if (!countKeySets.containsKey(count+1)) {
            countKeySets.put(count+1, new LinkedHashSet<>());
        }
        countKeySets.get(count+1).add(key);
        return keyValues.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) return ;
        // 缓存池中包含 key
        if (keyValues.containsKey(key)) {
            // 重新 put 一次，key 对应的 value 可能会发生变化
            keyValues.put(key, value);
            get(key);
            return;
        }

        // 存储的数据超过容量，就需要删除最少访问次数的 cache 并添加新的 cache
        if (keyValues.size() >= this.capacity) {
            // 获得访问次数最小的
            // 只移除一个就可以
            int leastFreq = countKeySets.get(min).iterator().next();
            keyValues.remove(leastFreq);
            keyCount.remove(leastFreq);
            countKeySets.get(min).remove(leastFreq);
        }
        // 添加新值
        keyValues.put(key, value);
        keyCount.put(key, 1);
        countKeySets.get(1).add(key);

        // 只要cache 中新添加一个，最小的访问次数必定为 1，而这个访问次数为1对应的 cache 就是新添加的 cache
        min = 1;
    }

    public static void main(String[] args) {
        LFUCacheSoulationII cache = new LFUCacheSoulationII( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);

        cache.put(3, 3);
        cache.get(2);
        cache.get(3);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);

    }
}