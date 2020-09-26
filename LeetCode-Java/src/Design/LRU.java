package Design;

import java.util.HashMap;
import java.util.Map;


/**
 * 每次淘汰最久没有使用的数据
 * @param <K>
 * @param <V>
 */
public class LRU<K, V> {
    final class Link {
        final class Node {
            V value;
            K key;
            Node prev;
            Node next;

            public Node(K key, V value, Node prev, Node next) {
                this.key = key;
                this.value = value;
                this.prev = prev;
                this.next = next;
            }

            public Node() {
                this(null, null, null, null);
            }

            public Node(K key, V value) {
                this(key, value, null, null);
            }
        }

        Node head;
        Node tail;

        private int size;

        Link() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public int getSize() {
            return size;
        }

        public void bringToHead(Node node) {
            node.next = head.next;
            head.next = node;
            node.prev = head;
        }

        public Node removeLastNode() {
            Node lastNode = tail.prev;
            lastNode.prev.next = lastNode.next;
            lastNode.next.prev = lastNode.prev;
            return lastNode;
        }
    }

    private Map<K, Link.Node> values;

    private Link valueLink;

    private int capacity;

    private int currentCount;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.values = new HashMap<>();
        this.valueLink = new Link();
        this.currentCount = 0;
    }

    void put(K key, V value) {
        Link.Node valueNode = values.get(key);
        // 重复 put 相同的元素
        if (valueNode.value.equals(value)) {
            valueLink.bringToHead(valueNode);
            return;
        }
        // 最近使用插入头部
        Link.Node addedValueNode = valueLink.new Node(key, value);
        values.put(key, addedValueNode);
        valueLink.bringToHead(addedValueNode);
        currentCount++;
        // Cache 已满，移除最后一个
        if (currentCount > capacity) {
            currentCount--;
            Link.Node lastNode = valueLink.removeLastNode();
            values.remove(lastNode.key);
        }
    }

    V get(K key) {
        Link.Node valueNode = values.get(key);
        if (valueNode != null) {
            valueLink.bringToHead(valueNode);
            return valueNode.value;
        }
        return null;
    }
}
