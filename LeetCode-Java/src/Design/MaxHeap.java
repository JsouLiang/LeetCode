package Design;

/**
 * 最大堆
 */
public class MaxHeap<V extends Comparable<V>> {
    private V[] values;
    private int currentCount;
    // p: 1 left: 2 right: 3
    // parent 下标为 1，左节点为 2，右节点为 3
    // 孩子找父亲：index / 2
    // 父亲找左孩子：index * 2
    // 父亲找右孩子：(index * 2) + 1
    public MaxHeap(int cap) {
        this.currentCount = 0;
        // 数组第 0 位不使用
        this.values = (V[]) new Comparable[cap + 1];
    }

    public V peekMax() {
        return values[1];
    }

    public void insert(V value) {
        currentCount++;
        // 插入到结尾
        values[currentCount] = value;
        // "上浮"维护堆特性
        swim(currentCount);
    }

    public V popMax() {
        V max = values[1];
        // 1. 先将最大的移动到最后
        exchange(1, currentCount);
        // 2. 移除最后一个元素
        values[currentCount] = null;
        currentCount--;
        // 3. 第一个元素下沉
        sink(1);
        return max;
    }

    /**
     * 上浮index 位置元素到合适位置
     */
    private void swim(int index) {
        //
        int parent = parent(index);
        while (parent >= 1 && values[parent].compareTo(values[index]) < 0) {
            exchange(parent, index);
            index = parent;
            parent = parent(index);
        }
    }

    /**
     * 下沉
     * 下沉时需要与两个节点进行比较，选取最大的与 parent 进行交换
     */
    private void sink(int index) {

        while (leftChildIndex(index) <= currentCount) {
            int left = leftChildIndex(index);
            int right = rightChildIndex(index);
            int older = left;

            if (right <= currentCount && values[left].compareTo(values[right]) < 0) {
                older = right;
            }
            // Parent > left & right
            if (values[index].compareTo(values[older]) > 0) {
                break;
            }
            exchange(older, index);
            index = older;
        }
    }

    /**
     * 孩子获取父节点的坐标
     * @return
     */
    private int parent(int child) {
        return child / 2;
    }

    /**
     * 父亲获取左孩子坐标
     */
    private int leftChildIndex(int parent) {
        return parent * 2;
    }

    /**
     * 父亲获取右孩子坐标
     */
    private int rightChildIndex(int parent) {
        return (parent * 2) + 1;
    }

    private void exchange(int index1, int index2) {
        V tempP = values[index1];
        values[index1] = values[index2];
        values[index2] = tempP;
    }


    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(10);
        maxHeap.insert(1);
        maxHeap.insert(2);
        maxHeap.insert(3);
        System.out.println(maxHeap.peekMax());
        maxHeap.popMax();
        maxHeap.popMax();
        maxHeap.insert(3);
        maxHeap.insert(2);
        maxHeap.insert(4);
        maxHeap.insert(5);
        System.out.println(maxHeap.peekMax());
        maxHeap.insert(5);
        maxHeap.popMax();
        System.out.println(maxHeap.peekMax());
    }

}
