package Math;

/**
 * 246. 中心对称数
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 * 请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。
 *
 */
public class LeetCode246 {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return false;
        }
        if (num.length() == 1) {
            return num.equals("0") || num.equals("1") || num.equals("8");
        }
        int left = 0, right = num.length() - 1;
        while (left < right) {
            char leftChar = num.charAt(left++), rightChar = num.charAt(right--);
            if (leftChar == rightChar && leftChar == '1' || leftChar == '8' || leftChar == '0') {
                continue;
            } else if (leftChar == '6' && rightChar == '9' || leftChar == '9' && rightChar == '6') {
                continue;
            } else {
                return false;
            }
        }
        if (left == right) {
            return num.charAt(left) == '0' || num.charAt(left) == '1' || num.charAt(left) == '8';
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode246 leetCode246 = new LeetCode246();
        boolean res = leetCode246.isStrobogrammatic("69");
        res = leetCode246.isStrobogrammatic("88");
        res = leetCode246.isStrobogrammatic("692");
        res = leetCode246.isStrobogrammatic("1");
        res = leetCode246.isStrobogrammatic("659");
    }
}
