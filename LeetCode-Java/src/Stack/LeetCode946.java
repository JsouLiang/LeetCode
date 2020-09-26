package Stack;

import java.util.Stack;

public class LeetCode946 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int poppedIndex = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            if (pushed[i] != popped[poppedIndex]) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() == popped[poppedIndex]) {
                poppedIndex++;
                stack.pop();
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode946 leetCode946 = new LeetCode946();
        leetCode946.validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2});
    }
}
