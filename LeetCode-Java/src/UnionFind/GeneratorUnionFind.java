package UnionFind;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GeneratorUnionFind<T> {
    private Map<T, Node<T>> nodes = new HashMap<>();

    public void makeSet(T t) {
        nodes.put(t, new Node<>(t));
    }

    public T find(T t) {
        Node<T> node = findRoot(t);
        return node == null ? null : node.value;
    }

    /**
     * 找到指定 T 的根节点
     * @param t
     * @return
     */
    private Node<T> findRoot(T t) {
        Node<T> node = nodes.get(t);
        if (node == null) {
            return null;
        }
        while (!Objects.equals(node.parent.value, node.value)) {
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }

    public void uinon(T value1, T value2) {
        Node<T> parent1 = findRoot(value1);
        Node<T> parent2 = findRoot(value2);
        if (parent1 == null || parent2 == null) {
            return;
        }
        if (Objects.equals(parent1.value, parent2.value)) return;
        if (parent1.rank < parent2.rank) {
            parent1.parent = parent2;
        } else if (parent1.rank > parent2.rank) {
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
            parent2.rank++;
        }
    }

    public boolean isSame(T value1, T value2) {
        return Objects.equals(find(value1), find(value2));
    }

    private class Node<T> {
        T value;
        Node<T> parent = this;
        /// 以当前节点为根节点时，树的高度
        int rank = 1;

        public Node(T value) {
            this.value = value;
        }

    }
}
