package Stack;

import java.util.Stack;

public class ReverseStack {
    private Stack<Integer> stack;

    ReverseStack(Stack<Integer> stack) {
        this.stack = stack;
    }

    private Integer getAndRemoveLasestElement() {
        Integer value = stack.pop();
        if (stack.isEmpty()) {
            return value;
        } else {
            Integer nextValue = getAndRemoveLasestElement();
            stack.push(value);
            return nextValue;
        }
    }

    public void reverse() {
        if (stack.isEmpty()) {
            return ;
        }
        int lastest = getAndRemoveLasestElement();
        reverse();
        stack.push(lastest);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        ReverseStack reverseStack = new ReverseStack(stack);
        reverseStack.reverse();

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}