package Link;

import java.util.HashMap;

/**
 * Link
 * Created by X-Liang
 * 2017-10-03-12:17
 *
 * @Description:
 * 为最近最少使用（LRU）缓存策略设计一个数据结构;
 * 它应该支持以下操作：获取数据（get）和写入数据（set）。
 * 获取数据get(key)：如果缓存中存在key，则获取其数据值（通常是正数），否则返回-1。
 * 写入数据set(key, value)：如果key还没有在缓存中，则写入其数据值。
 * 当缓存达到上限，它应该在写入新数据之前删除最近最少使用的数据用来腾出空闲位置。
 *
 * Solution：
 * 1. 使用条双向链表，双向链表每个节点是缓存的 key 与他对应的缓存值
 * 2. 双向链表的排序就是每个缓存节点最近的使用时间，第一个最近使用的时间距离当前时间最短，最后一个最近使用的时间距离当前时间最长
 * 3. 使用一个 Hashtable 保存每个 key 与之对应的缓存节点，方便根据 key 快速定位需要的缓存节点
 */
final class LinkNode {
    // 缓存的 key
    int key;
    // 缓存的值
    int value;
    LinkNode pre;
    LinkNode next;

    LinkNode(int key, int value) {
        this.key = key;
        this.value = value;
        pre = null; next = null;
    }

    LinkNode() {
        key = Integer.MAX_VALUE;
        value = Integer.MAX_VALUE;
        pre = null;
        next = null;
    }
}
public class LRUCache {

    /**
     * key：缓存节点对应的 key
     * value：缓存 node
     */
    private HashMap<Integer, LinkNode> cache = new HashMap<>();
    private int count;
    private int capacity;
    /**
     * Link 由头到尾依次按最近使用的时间排序，第一个最近使用的时间距离当前时间最短，最后一个最近使用的时间距离当前时间最长
     * 我们这里的 head和 tail 是一个 dummyNode，
     * 头部真正的值为 head.next 节点，尾部真正的值为 tail.pre
     */
    private LinkNode head, tail;

    /**
     * LRU cache 的容纳力
     * 初始化时，创建缓存链表，头节点 head 跟尾节点 tail 连接
     * count 初始化为 0
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new LinkNode();
        tail = new LinkNode();

        head.next = tail;
        tail.pre = head;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // 获得 key 对应的链表节点
        LinkNode node = cache.get(key);

        if (node == null) {
            return -1;
        }
        // 刚刚获得 key 对应的 cache，所有该 cache 要保存的 Link 头部
        this.moveToHead(node);

        return node.value;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        LinkNode node = cache.get(key);
        // 当前没有key 对应的缓存
        if (node == null) {
            LinkNode newNode = new LinkNode(key, value);
            this.cache.put(key, newNode);
            this.addNode(newNode);
            // 缓存节点的个数++
            count++;

            // 当前缓存的个数超过最大容量
            if (count > this.capacity) {
                LinkNode tail = this.popTail();
                this.cache.remove(tail.key);
                count--;
            }

        } else {
            // 更新缓存节点的值
            node.value = value;
            // 将缓存节点移动到链表顶端
            this.moveToHead(node);
        }
    }

    /**
     * 往双向链表中添加一个节点
     * @param addedNode 被添加的节点
     */
    private void addNode(LinkNode addedNode) {
        addedNode.pre = head;
        addedNode.next = head.next;

        head.next.pre = addedNode;
        head.next = addedNode;
    }

    /**
     * 移除一个指定的节点
     * @param removedNode  指定移除的节点
     */
    private void removeNode(LinkNode removedNode) {
        // removedNode 的前一个节点的 next 变为 removedNode 的下一个节点
        removedNode.pre.next = removedNode.next;
        // removedNode 的下一个节点的 pre 变为 removedNode 的前一个节点
        removedNode.next.pre = removedNode.pre;
    }

    /**
     * 将指定的节点移动到最头上
     * @param movedNode 被移动的节点
     */
    private void moveToHead(LinkNode movedNode) {
        this.removeNode(movedNode);
        this.addNode(movedNode);
    }

    /**
     * 将链表尾部节点移除，并返回被移除的节点
     * @return 被移除的尾部节点
     */
    private LinkNode popTail() {
        LinkNode realNode = tail.pre;
        this.removeNode(realNode);
        return realNode;
    }

}
