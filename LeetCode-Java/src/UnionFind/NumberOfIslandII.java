package UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定 n, m, 分别代表一个二维矩阵的行数和列数, 并给定一个大小为 k 的二元数组A.
 * 初始二维矩阵全0. 二元数组A内的k个元素代表k次操作,
 * 设第i个元素为 (A[i].x, A[i].y), 表示把二维矩阵中下标为A[i].x行A[i].y列的元素由海洋变为岛屿.
 * 问在每次操作之后, 二维矩阵中岛屿的数量. 你需要返回一个大小为k的数组.
 * 输入: n = 4, m = 5, A = [[1,1],[0,1],[3,3],[3,4]]
 * 输出: [1,1,2,2]
 * 解释:
 * 0.  00000
 *     00000
 *     00000
 *     00000
 * 1.  00000
 *     01000
 *     00000
 *     00000
 * 2.  01000
 *     01000
 *     00000
 *     00000
 * 3.  01000
 *     01000
 *     00000
 *     00010
 * 4.  01000
 *     01000
 *     00000
 *     00011
 *
 * 输入: n = 3, m = 3, A = [[0,0],[0,1],[2,2],[2,1]]
 * 输出: [1,1,2,2]
 */
public class NumberOfIslandII {

    static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
//    static class UnionFind<T, R> {
//        interface UnionFindEncodeable<T, R> {
//            public R encodable(T t);
//        }
//
//        public UnionFind(UnionFindEncodeable encodeable) {
//            this.encodeable = encodeable;
//        }
//
//        private UnionFindEncodeable<T, R> encodeable;
//
//        private Map<R, T> father = new HashMap<>();
//
//        private int connectedComponentCount;
//
//        public int getConnectedComponentCount() {
//            return connectedComponentCount;
//        }
//
//        public void addSingle(T a) {
//            father.put(encodeable.encodable(a), a);
//            connectedComponentCount++;
//        }
//
//        public void union(T a, T b) {
//            T aG = find(a);
//            T bG = find(b);
//            if (aG != bG) {
//                father.put(encodeable.encodable(bG), aG);
//                connectedComponentCount--;
//            }
//        }
//
//        public T find(T a) {
//            List<T> path = new ArrayList<>();
//            while (father.get(encodeable.encodable(a)) != a) {
//                path.add(a);
//                a = father.get(encodeable.encodable(a));
//            }
//            for (T t : path) {
//                father.put(encodeable.encodable(t), a);
//            }
//            return a;
//        }
//    }

    static class UnionFind{

        private Map<Integer, Integer> father = new HashMap<>();

        private int connectedComponentCount;

        public int getConnectedComponentCount() {
            return connectedComponentCount;
        }

        public void addSingle(Integer a) {
            father.put(a, a);
            connectedComponentCount++;
        }

        public void union(Integer a, Integer b) {
            Integer aG = find(a);
            Integer bG = find(b);
            if (aG != bG) {
                father.put(bG, aG);
                connectedComponentCount--;
            }
        }

        public Integer find(Integer a) {
            List<Integer> path = new ArrayList<>();
            while (father.get(a) != a) {
                path.add(a);
                a = father.get(a);
            }
            for (Integer t : path) {
                father.put(t, a);
            }
            return a;
        }
    }

    private int encodePoint(Point p, int column) {
//        注意这种 encode 方式会有问题：Point(2, 17) 会与 Point(3, 7) 获得相同的值
//        return p.x * 10 + p.y;
        return p.x * column + p.y;
    }
        /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> res = new ArrayList<>();
        if (operators == null || operators.length == 0) {
            return res;
        }
        UnionFind unionFind = new UnionFind();

        boolean[][] isIsland = new boolean[n][m];
        int[] dist = {-1, 0, 1, 0, -1};


        for (Point point : operators) {
            if (isIsland[point.x][point.y]) {
                res.add(unionFind.getConnectedComponentCount());
                continue;
            }
            unionFind.addSingle(encodePoint(point, m));
            isIsland[point.x][point.y] = true;

            for (int i = 0; i < 4; i++) {
                int nbX = point.x + dist[i];
                int nbY = point.y + dist[i + 1];

                if (nbX >= 0 && nbX < n && nbY >= 0 && nbY < m && isIsland[nbX][nbY]) {
                     unionFind.union(encodePoint(new Point(nbX, nbY), m), encodePoint(point, m));
                }
            }
            res.add(unionFind.getConnectedComponentCount());
        }
        return res;
    }

    public static void main(String[] args) {
        NumberOfIslandII numberOfIslandII = new NumberOfIslandII();
        List res = numberOfIslandII.numIslands2(4, 5, new Point[]{
                new Point(1,1),
                new Point(0,1),
                new Point(3,3),
                new Point(3,4),
        });
        res = numberOfIslandII.numIslands2(3, 3, new Point[]{
                new Point(0,0),
                new Point(0,1),
                new Point(2,2),
                new Point(2,1),
        });
        res = numberOfIslandII.numIslands2(3, 3, new Point[]{
                new Point(0,0),
                new Point(0,1),
                new Point(2,2),
                new Point(2,2),
        });
        res = numberOfIslandII.numIslands2(2, 2, new Point[]{
                new Point(0,0),
                new Point(1,1),
                new Point(1,0),
                new Point(0,1),
        });
        res = numberOfIslandII.numIslands2(12, 20, new Point[]{
                new Point(6,2),
                new Point(0,9),
                new Point(0,6),
                new Point(9,3),
        });
        System.out.println(res);
    }
}
