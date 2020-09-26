package Math;

public class LeetCode836 {
    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        Point rect1Point1 = new Point(rec1[0], rec1[1]);
        Point rect1Point2 = new Point(rec1[2], rec1[3]);
        Point rect2Point1 = new Point(rec2[0], rec2[1]);
        Point rect2Point2 = new Point(rec2[2], rec2[3]);
        /// x 想交
        boolean isOverlapX = !((rect1Point2.x <= rect2Point1.x) || (rect2Point2.x <= rect1Point1.x));
        /// y 想交
        boolean isOverlapY = !((rect1Point2.y <= rect2Point1.y) || (rect2Point2.y <= rect1Point1.y));
        return isOverlapX && isOverlapY;
    }

    public static void main(String[] args) {
        LeetCode836 leetCode836 = new LeetCode836();
        leetCode836.isRectangleOverlap(new int[]{2,17,6,20}, new int[]{3,8,6,20});
    }
}
