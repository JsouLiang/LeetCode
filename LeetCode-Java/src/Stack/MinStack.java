package Stack;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> minStack;
    private Stack<Integer> valueStack;

    public MinStack() {
        minStack = new Stack<>();
        valueStack = new Stack<>();
    }

    public void push(int x) {
        // 看minStack是否为空
        if (minStack.isEmpty()) {
            // 直接push
            minStack.push(x);
            valueStack.push(x);
        } else {
            // 取出minStack栈顶，比较
            int minestValue = minStack.peek();
            if (minestValue > x) {
                minStack.push(x);
            } else {
                minStack.push(minestValue);
            }
            valueStack.push(x);
        }
    }

    public void pop() {
        valueStack.pop();
        minStack.pop();
    }

    public int top() {
        return valueStack.peek();
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        return -1;
    }
}
