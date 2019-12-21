package Stack.单调栈;
import javafx.util.Pair;

import java.util.Stack;
/**
 * 739. 每日温度
 */
public class LeetCode739 {
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) {
            return new int[]{0};
        }
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            result[i] = 0;
        }
        // 递减栈
        Stack<Pair<Integer, Integer>> decreaseStack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            if (!decreaseStack.isEmpty()) {
                while (!decreaseStack.isEmpty() && decreaseStack.peek().getKey() < T[i]) {
                    result[decreaseStack.peek().getValue()] = i - decreaseStack.peek().getValue();
                    decreaseStack.pop();
                }
            }
            decreaseStack.push(new Pair(T[i], i));
        }
        return result;
    }
}
