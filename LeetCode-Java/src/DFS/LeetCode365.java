package DFS;

import javafx.util.Pair;

import java.util.*;

public class LeetCode365 {
    int[] jugs;
    Set<State> visitedState;
    /// 0：未初始化
    /// 1: 可达
    /// 2: 不可达
    int[][] canMeasure;

    class State {
        int x;
        int y;

        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            State state = (State) o;
            return x == state.x &&
                    y == state.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (z == 0) {
            return true;
        }
        if (x + y < z) {
            return false;
        }
//        jugs = new int[]{x, y};
        visitedState = new HashSet<>();
        boolean res = findTargetBFS(x, y, z);
        return res;
    }

    private boolean findTargetBFS(int x, int y, int target) {
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0));
        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            if (currentState.x == target || currentState.y == target || currentState.x + currentState.y == target) {
                return true;
            }
            List<State> states = generatorNextStates(currentState.x, currentState.y, x, y);
            for (State nextState: states) {
                if (!visitedState.contains(nextState)) {
                    visitedState.add(nextState);
                    queue.offer(nextState);
                }
            }
        }
        return false;
    }

    List<State> generatorNextStates(int used_x, int used_y, int x, int y) {
        List<State> states = new ArrayList<>();
        /// 把 x 灌满
        State state0 = new State(x, used_y);
        /// 把 y 灌满
        State state1 = new State(used_x, y);
        /// 把 x 倒空
        State state2 = new State(0, used_y);
        /// 把 y 倒空
        State state3 = new State(used_x, 0);
        /// 把 x 倒入 y 中，直到倒满 y 或倒空 x
        State state4 = new State(used_x - Math.min(used_x, jugs[1] - used_y),   ///x 壶已使用或者y 壶剩余的取最小的
                used_y + Math.min(used_x, jugs[1] - used_y));

        /// 把 y 倒入 x 中，直到倒满 x 或者清空 y
        State state5 = new State(used_x + Math.min(used_y, jugs[0] - used_x), used_y - Math.min(used_y, jugs[0] - used_x));

        /// x 壶未满，装满 x 壶才有意义
        if (used_x < x) {
            states.add(state0);
        }
        /// y 壶未满，装满 y 壶才有意义
        if (used_y < y) {
            states.add(state1);
        }
        states.add(state2);
        states.add(state3);
        if (used_x > 0) {
            states.add(state4);
        }
        if (used_y > 0) {
            states.add(state5);
        }
        return states;
    }

    private boolean findTargetDFS(int used_x, int used_y, int target) {
        if (canMeasure[used_x][used_y] != 0) {
            return canMeasure[used_x][used_y] == 1;
        }
        if (used_x == target || used_y == target || used_x + used_y == target) {
            canMeasure[used_x][used_y] = 1;
            return true;
        }
        State[] states = new State[]{};
        /// 把 x 灌满
        State state0 = new State(jugs[0], used_y);
        states[0] = state0;
        /// 把 y 灌满
        State state1 = new State(used_x, jugs[1]);
        states[1] = state1;
        /// 把 x 倒空
        State state2 = new State(0, used_y);
        states[2] = state2;
        /// 把 y 倒空
        State state3 = new State(used_x, 0);
        states[3] = state3;
        /// 把 x 倒入 y 中，直到倒满 y 或倒空 x
        State state4 = new State(used_x - Math.min(used_x, jugs[1] - used_y),     ///x 壶已使用或者y 壶剩余的取最小的
                used_y + Math.min(used_x, jugs[1] - used_y));
        states[4] = state4;
        /// 把 y 倒入 x 中，直到倒满 x 或者清空 y
        State state5 = new State(used_x + Math.min(used_y, jugs[0] - used_x),
                used_y - Math.min(used_y, jugs[0] - used_x));
        states[5] = state5;

        for (int i = 0; i < 6; i++) {
            State currentState = states[i];
            if (!visitedState.contains(currentState)) {
                visitedState.add(currentState);
                boolean res = findTargetDFS(currentState.x, currentState.y, target);
                canMeasure[currentState.x][currentState.y] = res == true ? 1 : 2;
                if (res) {
                    return true;
                }
                visitedState.remove(currentState);
            }
        }
        canMeasure[used_x][used_y] = 2;
        return false;
    }

    public static void main(String[] args) {
        LeetCode365 leetCode365 = new LeetCode365();
        leetCode365.canMeasureWater(22003, 31237, 1);
    }
}
