package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 694. 不同岛屿的数量
 * 给定一个非空01二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，你可以认为网格的四周被海水包围。
 * <p>
 * 请你计算这个网格中共有多少个形状不同的岛屿。两个岛屿被认为是相同的，
 * 当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。
 */
abstract class Direction {
    abstract int nextI(int i);

    abstract int nextJ(int j);

    abstract String directionId();

    abstract String reverseDirectionId();
}

class Up extends Direction {
    @Override
    int nextI(int i) {
        return i - 1;
    }

    @Override
    int nextJ(int j) {
        return j;
    }

    @Override
    String directionId() {
        return "Up";
    }

    @Override
    String reverseDirectionId() {
        return "Down";
    }
}

class Down extends Direction {
    @Override
    int nextI(int i) {
        return i + 1;
    }

    @Override
    int nextJ(int j) {
        return j;
    }

    @Override
    String directionId() {
        return "Down";
    }

    @Override
    String reverseDirectionId() {
        return "Up";
    }
}

class Left extends Direction {
    @Override
    int nextI(int i) {
        return i;
    }

    @Override
    int nextJ(int j) {
        return j - 1;
    }

    @Override
    String directionId() {
        return "Left";
    }

    @Override
    String reverseDirectionId() {
        return "Right";
    }
}

class Right extends Direction {
    @Override
    int nextI(int i) {
        return i;
    }

    @Override
    int nextJ(int j) {
        return j + 1;
    }

    @Override
    String directionId() {
        return "Right";
    }

    @Override
    String reverseDirectionId() {
        return "Left";
    }
}

public class LeetCode694 {

    private Direction[] directions = {new Up(), new Down(), new Right(), new Left()};
    private int[][] gird;
    private boolean[][] visited;

    public int numDistinctIslands(int[][] grid) {
        this.gird = grid;
        if (gird == null || grid.length == 0) {
            return 0;
        }
        visited = new boolean[gird.length][gird[0].length];
        Set<String> islands = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    String islandId = generatorIsland(i, j);
                    islands.add(islandId);
                }
            }
        }
        islands.size();
        return islands.size();
    }

    private String generatorIsland(int i, int j) {
        gird[i][j] = 0;
        StringBuilder islandInfo = new StringBuilder();
        if (canVisited(i - 1, j)) {
            islandInfo.append("Up");
            islandInfo.append(generatorIsland(i - 1, j));
        }

        if (canVisited(i + 1, j)) {
            islandInfo.append("Down");
            islandInfo.append(generatorIsland(i + 1, j));
        }

        if (canVisited(i, j - 1)) {
            islandInfo.append("Left");
            islandInfo.append(generatorIsland(i, j - 1));
        }
        if (canVisited(i, j + 1)) {
            islandInfo.append("Right");
            islandInfo.append(generatorIsland(i, j + 1));
        }


//        for (Direction direction: directions) {
//            int nextI = direction.nextI(i);
//            int nextJ = direction.nextJ(j);
//            if (nextI >= 0 && nextJ >= 0 && nextI < gird.length && nextJ < gird[0].length) {
//                if (gird[nextI][nextJ] == 1) {
//                    gird[nextI][nextJ] = 0;
//                    islandInfo.append(direction.directionId());
//                    islandInfo.append(generatorIsland(nextI, nextJ));
//                    islandInfo.append(direction.reverseDirectionId());
//                }
//            }
//        }
        return islandInfo.toString();
    }

    private boolean canVisited(int i, int j) {
        return i >= 0 && j >= 0 && i < gird.length && j < gird[0].length && gird[i][j] == 1;
    }

    public static void main(String[] args) {
        LeetCode694 leetCode694 = new LeetCode694();
        leetCode694.numDistinctIslands(new int[][]{
                {1, 1, 0,},
                {0, 1, 1},
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 0},
        });
    }
}
