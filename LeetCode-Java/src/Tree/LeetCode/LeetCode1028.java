package Tree.LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 1028. 从先序遍历还原二叉树
 */
enum State {
    initial,
    num,
    other
}
public class LeetCode1028 {
    public TreeNode recoverFromPreorder(String S) {
        char[] chars = S.toCharArray();
        Deque<TreeNode> stack = new ArrayDeque<>();

        int currentNum = 0;
        int index = 0;
        int currentLevel = 0;
        State state = State.initial;
        while (index < chars.length) {
            char currentChar = chars[index];

            switch (state) {
                case initial: {
                    if (isNum(currentChar)) {
                        state = State.num;
                        currentNum = currentNum * 10 + toNum(currentChar);
                        index++;
                    }
                    break;
                }
                case num: {
                    if (isNum(currentChar)) {
                        state = State.num;
                        currentNum = currentNum * 10 + toNum(currentChar);
                        index++;
                    } else {
                        createTreeNode(stack, currentNum, currentLevel);
                        state = State.other;
                        currentLevel = 0;
                    }
                    break;
                }
                case other: {
                    if (!isNum(currentChar)) {
                        state = State.other;
                        currentLevel++;
                    } else {
                        state = State.num;
                        currentNum = toNum(currentChar);
                    }
                    index++;
                    break;
                }
            }
        }
        createTreeNode(stack, currentNum, currentLevel);
        return stack.isEmpty() ? null : stack.peekLast() ;
    }

    private void createTreeNode(Deque<TreeNode> stack, int currentNum, int level) {
        TreeNode node = new TreeNode(currentNum);
        while (stack.size() > level) {
            stack.pop();
        }
        TreeNode parent = stack.isEmpty() ? null : stack.peek();
        if (parent != null) {
            if (parent.left == null) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
        stack.push(node);
    }

    private boolean isNum(char ca) {
        return ca >= '0' && ca <= '9';
    }

    private int toNum(char ca) {
        return ca - '0';
    }

    public static void main(String[] args) {
        LeetCode1028 leetCode1028 = new LeetCode1028();
        leetCode1028.recoverFromPreorder("1-2--3--4-5--6--7");
        leetCode1028.recoverFromPreorder("1-2--3---4-5--6---7");
        leetCode1028.recoverFromPreorder("1-401--349---90--88");
    }
}
