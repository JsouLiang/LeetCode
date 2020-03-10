package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 490. 迷宫
 * 由空地和墙组成的迷宫中有一个球。球可以向上下左右四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。
 * <p>
 * 给定球的起始位置，目的地和迷宫，判断球能否在目的地停下。
 * <p>
 * 迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。
 */
public class LeetCode490 {
    enum Direction {
        left,
        right,
        up,
        down
    }

    private int[] direct = {-1, 0, 1, 0, -1, 0};

    private boolean[][] visited;
    private int[] destionation;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        this.destionation = destination;
        visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;
        boolean res = helper(maze, start[0], start[1]);
        return res;
    }

    private boolean helper(int[][] maze, int x, int y) {
        for (int i = 0; i < direct.length - 1; i++) {
            int deltaX = direct[i];
            int nextX = x + deltaX;
            int deltaY = direct[i + 1];
            int nexY = y + deltaY;
            if (nextX >= 0 && nexY >= 0 && nextX < maze.length && nexY < maze[0].length && maze[nextX][nexY] != 1) {
                Direction direction;
                if (deltaX > 0) {
                    direction = Direction.right;
                } else if (deltaX < 0) {
                    direction = Direction.left;
                } else if (deltaY > 0) {
                    direction = Direction.down;
                } else {
                    direction = Direction.up;
                }
                return dfsWidthDirection(maze, nextX, nexY, direction);
            }
        }
        return false;
    }

    private boolean dfsWidthDirection(int[][] maze, int x, int y, Direction direction) {
        if (x == destionation[0] && y == destionation[1]) {
            return true;
        }
        int nextX = x;
        int nextY = y;
        boolean checkDirection = false;
        List<Integer[]> directions = new ArrayList<>();
        switch (direction) {
            case left:
                nextX = x - 1;
                /// 向左走是墙
                if (nextX < 0 || maze[nextX][y] == 1) {
                    checkDirection = true;
                    directions.add(new Integer[]{1, 0});
                    directions.add(new Integer[]{0, 1});
                    directions.add(new Integer[]{0, -1});
                }
            case right:
                nextX = x + 1;
                if (nextX >= maze[0].length || maze[nextX][y] == 1) {
                    checkDirection = true;
                    directions.add(new Integer[]{-1, 0});
                    directions.add(new Integer[]{0, 1});
                    directions.add(new Integer[]{0, -1});
                }
            case up:
                nextY = y - 1;
                /// up is wall
                if (nextY < 0 || maze[x][nextY] == 1) {
                    checkDirection = true;
                    directions.add(new Integer[]{-1, 0});
                    directions.add(new Integer[]{1, 0});
                    directions.add(new Integer[]{0, 1});
                }
            case down:
                nextY = y + 1;
                if (nextY >= maze.length || maze[x][nextY] == 1) {
                    checkDirection = true;

                    directions.add(new Integer[]{-1, 0});
                    directions.add(new Integer[]{1, 0});
                    directions.add(new Integer[]{0, -1});
                }
        }

        if (!checkDirection) {
            return dfsWidthDirection(maze, nextX, nextY, direction);
        } else {
            for (Integer[] nextDirectionDelta : directions) {
                Direction nextDirection = direction;
                if (nextDirectionDelta[0] == 1) {
                    nextDirection = Direction.right;
                }else if (nextDirectionDelta[0] == -1) {
                    nextDirection = Direction.left;
                } else if (nextDirectionDelta[1] == 1) {
                    nextDirection = Direction.up;
                } else if (nextDirectionDelta[1] == -1) {
                    nextDirection = Direction.down;
                }
                nextX = x + nextDirectionDelta[0];
                nextY = y + nextDirectionDelta[1];
                if (!visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    boolean res = dfsWidthDirection(maze, nextX, nextY, nextDirection);
                    if (!res) {
                        visited[nextX][nextY] = false;
                    } else {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LeetCode490 leetCode490 = new LeetCode490();
        leetCode490.hasPath(new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}}, new int[]{0, 4}, new int[]{3, 2});
    }
}
