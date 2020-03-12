package Stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LeetCode716 {
    List<Integer> nums;
    List<Integer> maxStack;

    /** initialize your data structure here. */
    public LeetCode716() {
        nums = new LinkedList<>();
        maxStack = new LinkedList<>();
    }

    public void push(int x) {
        nums.add(0, x);
        if (!maxStack.isEmpty()) {
            int i = 0;
            for ( ;i < maxStack.size(); i++) {
                if (maxStack.get(i) <= x) {
                   break;
                }
            }
            maxStack.add(i, x);
            return;
        }
        maxStack.add(x);

    }

    public int pop() {
        int value = nums.remove(0);
        int i = 0;
        for (; i < maxStack.size(); i++) {
            if (maxStack.get(i) == value) {
                break;
            }
        }
        maxStack.remove(i);
        return value;
    }

    public int top() {
        return nums.get(0);
    }

    public int peekMax() {
        return maxStack.get(0);
    }

    public int popMax() {
        int max = maxStack.remove(0);

        int i = 0;
        for (; i < nums.size(); i++) {
            if (nums.get(i) == max) {
                break;
            }
        }
        nums.remove(i);
        return max;
    }

    public static void main(String[] args) {
        LeetCode716 maxStack = new LeetCode716();
        maxStack.push(5);
        maxStack.push(1);
//        int val = maxStack.top();
       int val = maxStack.popMax();
        val = maxStack.peekMax();
    }
}
