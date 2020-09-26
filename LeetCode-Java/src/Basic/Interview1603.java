package Basic;

import javax.swing.*;

public class Interview1603 {
    public double[] intersectionMySolution(int[] start1, int[] end1, int[] start2, int[] end2) {
        /// line1: y = line1A * x + line1B
        Double line1B = null;
        Double line1A = null;
        Double pointX = null;
        Double pointY = null;
        if (end1[0] - start1[0] == 0) {
            /// 垂直 X 轴
            pointX = (double) start1[0];
        } else if (end1[1] - start1[1] == 0) {
            /// 垂直 y 轴
            pointY = (double) start1[1];
            line1A = 0.0;
            line1B = pointY;
        } else {
            line1A = (double) (end1[1] - start1[1]) / (end1[0] - start1[0]);
            line1B = start1[1] - line1A * start1[0];
        }

        Double line2B = null;
        Double line2A = null;
        if (end2[0] - start2[0] == 0) {
            double line2PointX = start2[0];
            if (pointX != null) {
                double[] res = new double[2];
                /// 两条垂直 X 轴，且平行的直线
                if (line2PointX == pointX) {
                    res[0] = pointX;

                    return res;
                }
                return new double[]{};
            } else {
                pointX = line2PointX;
            }
            if (pointY != null) {
                return new double[]{pointX, pointY};
            }
        } else if (end2[1] - start2[1] == 0) {
            double line2PointY = start2[1];
            if (pointY != null) {
                double[] res = new double[2];
                if (line2PointY == pointY) {
                    res[0] = Math.min(Math.min(start1[0], start2[0]), Math.min(end1[0], end2[0]));
                    ;
                    res[1] = pointY;
                    return res;
                }
                /// 两条垂直 Y 轴，且平行的直线
                return new double[]{};
            } else {
                pointY = line2PointY;
            }
            line2A = 0.0;
            line2B = pointY;
            if (pointX != null) {
                return new double[]{pointX, pointY};
            }
        } else {
            /// line2
            line2A = (double) (end2[1] - start2[1]) / (end2[0] - start2[0]);
            line2B = start2[1] - line2A * start2[0];

            if (pointX != null) {
                pointY = line2A * pointX + line2B;
                return new double[]{pointX, pointY};
            }
            if (pointY != null) {
                pointX = (pointY - line2B) / line2A;
                return new double[]{pointX, pointY};
            }
        }
        if (line1A.equals(line2A)) {
            /// 平行
            if (!line1B.equals(line2B)) {
                return new double[]{};
            }
            int minLine1X = Math.min(start1[0], end1[0]);
            int maxLine1X = Math.max(start1[0], end1[0]);
            int minLine2X = Math.min(start2[0], end2[0]);
            int maxLine2X = Math.max(start2[0], end2[0]);
            if (maxLine1X < minLine2X || maxLine2X < minLine1X) {
                return new double[]{};
            }
            if (minLine2X > minLine1X) {
                return new double[]{minLine2X, line1A * minLine2X};
            }
            if (minLine1X > minLine2X && minLine1X <= maxLine1X) {
                return new double[]{maxLine1X, line1A * maxLine1X};
            }
        }

        if (pointX == null) {
            pointX = (line2B - line1B) / (line1A - line2A);
        }
        if (pointY == null) {
            pointY = (line1A * pointX) + line1B;
        }

        return new double[]{pointX, pointY};
    }

    class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        void setLocation(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    class Line {
        Point start;
        Point end;
        double slope;   /// 斜率
        double yintercept;  /// 截距

        /// y = slope * x + yintercept;
        Line(Point start, Point end) {
            this.start = start;
            this.end = end;
            double deltaX = end.x - start.x;
            double deltaY = end.y - start.y;

            if (deltaX == 0) {
                slope = Double.NaN;  /// deltaX 为 0 时，斜率无穷大
                yintercept = end.x;     /// x 轴上截距
            } else {
                slope = deltaY / deltaX;
                yintercept = end.y - slope * end.x;
            }

        }
    }

    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        Point[] points = generatorPoint(start1, end1, start2, end2);
        Point line1Start = points[0], line1End = points[1];
        Point line2Start = points[2], line2End = points[3];
        Line line1 = new Line(line1Start, line1End);
        Line line2 = new Line(line2Start, line2End);
        Point intersection;
        if (Double.isNaN(line1.slope) || Double.isNaN(line2.slope)) {
            if (Double.isNaN(line1.slope) && Double.isNaN(line2.slope)) {
                if (isEqual(line1.yintercept, line2.yintercept) && pointBetween(line1.start, line2.start, line1.end)) {
                    return new double[]{line2Start.x, line2Start.y};
                }
                return new double[0];
            }
            if (Double.isNaN(line1.slope)) {
                intersection = new Point(line1.yintercept, line1.yintercept * line2.slope + line2.yintercept);
            } else {
                intersection = new Point(line2.yintercept, line2.yintercept * line1.slope + line1.yintercept);
            }
        } else if (isEqual(line1.slope, line2.slope)) {
            /// 截距相等
            if (isEqual(line1.yintercept, line2.yintercept) && pointBetween(line1.start, line2.start, line1.end)) {
                return new double[]{line2Start.x, line2Start.y};
            }
            return new double[0];
        } else {
            double x = (line2.yintercept - line1.yintercept) / (line1.slope - line2.slope);
            double y = x * line1.slope + line1.yintercept;
            intersection = new Point(x, y);
        }
        /// 交点必须在line1，和 line2 之间
        if (pointBetween(line1Start, intersection, line1End) && pointBetween(line2Start, intersection, line2End)) {
            return new double[]{intersection.x, intersection.y};
        }
        return new double[0];
    }

    boolean pointBetween(Point start, Point middle, Point end) {
        return isBetween(start.x, middle.x, end.x) && isBetween(start.y, middle.y, end.y);
    }

    boolean isBetween(double start, double middle, double end) {
        if (start > end) {
            return end <= middle && middle <= start;
        }
        return start <= middle && middle <= end;
    }

    boolean isEqual(double value1, double value2) {
        double epslion = 1e-6f;

        if (Math.abs(value1 - value2) <= epslion) {
            return true;
        }
        return false;
    }

    Point[] generatorPoint(int[] start1, int[] end1, int[] start2, int[] end2) {
        Point line1StartPoint = new Point(start1[0], start1[1]);
        Point line1EndPoint = new Point(end1[0], end1[1]);
        Point line2StartPoint = new Point(start2[0], start2[1]);
        Point line2EndPoint = new Point(end2[0], end2[1]);

        if (line1StartPoint.x > line1EndPoint.x || (line1StartPoint.x == line1EndPoint.x && line1StartPoint.y > line1EndPoint.y)) {
            swap(line1StartPoint, line1EndPoint);
        }

        if (line2StartPoint.x > line2EndPoint.x || (line2StartPoint.x == line2EndPoint.x && line2StartPoint.y > line2EndPoint.y)) {
            swap(line2StartPoint, line2EndPoint);
        }

        if (line1StartPoint.x > line2StartPoint.x || (line1StartPoint.x == line2StartPoint.x && line1StartPoint.y > line2StartPoint.y)) {
            swap(line1StartPoint, line2StartPoint);
            swap(line1EndPoint, line2EndPoint);
        }

        return new Point[]{line1StartPoint, line1EndPoint, line2StartPoint, line2EndPoint};
    }

    void swap(Point point1, Point point2) {
        double tempX = point1.x;
        double tempY = point1.y;

        point1.x = point2.x;
        point1.y = point2.y;

        point2.x = tempX;
        point2.y = tempY;
    }

    public static void main(String[] args) {
        Interview1603 interview1603 = new Interview1603();
//        interview1603.intersection(new int[]{0, 0}, new int[]{1, 0}, new int[]{1, 1}, new int[]{0, -1});
//        interview1603.intersection(new int[]{0, 0}, new int[]{3, 3}, new int[]{1, 1}, new int[]{2, 2});
//        interview1603.intersection(new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 1}, new int[]{0, 2});
        interview1603.intersection(new int[]{0, 3}, new int[]{0, 6}, new int[]{0, 1}, new int[]{0, 5});
        interview1603.intersection(new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 0}, new int[]{0, -1});
    }
}
