package Basic;

public class LeetCode7 {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        /// 因为int有越界风险，所以这里使用 long
        long res = 0;
        while(x != 0) {
            res = (res * 10) + (x % 10);
            /// 判断是否 int 越界
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
                return 0;
            }
            x /= 10;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        LeetCode7 leetCode7 = new LeetCode7();
//        leetCode7.reverse(901000);
        leetCode7.reverse(1534236469);

    }
}


