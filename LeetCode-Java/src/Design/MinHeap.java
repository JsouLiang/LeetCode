package Design;

public class MinHeap {
    int capacity;
    int[] values;
    int currentCount = 0;
    public MinHeap(int capacity) {
        this.capacity = capacity; this.values = new int[capacity + 1];
    }

    int pop() {
        int val = values[1];
        exchange(1, currentCount);
        values[currentCount] = 1;
        currentCount--;
        sink(1);
        return val;
    }

    void insert(int val) {
        values[currentCount++] = val;
    }

    // 上浮
    void swim(int index) {
        int parent = parent(index);
        while (parent != 1 && parent > values[index]) {
            exchange(parent, index);
            parent = parent(index);
        }
    }

    // 下沉
    void sink(int index) {
        int left = left(index);
        while (left < currentCount && values[left] < values[index]) {
            int letter = left;
            int right = right(index);
            if (right <= capacity && values[right] < values[left]) {
                letter = right;
            }
            if (values[index] < values[letter]) {
                break;
            }
            exchange(index, letter);
            index = left;
            left = left(index);
        }
    }

    int parent(int index) {
        return index / 2;
    }

    int left(int index) {
        return index * 2;
    }

    int right(int index) {
        return index * 2 + 1;
    }

    void exchange(int from, int to) {
        int tmp = values[from];
        values[from] = values[to];
        values[to] = tmp;
    }
}