package Basic.string;

/**
 * 大数相加
 */
public class LeetCode415 {
    public String addStrings(String num1, String num2) {
        if(num1.equals("0") && num2.equals("0")) {
            return "0";
        }
        final int num1Length = num1.length();
        final int num2Length = num2.length();
        final int resLength = num1Length + num2Length;
        int[] res = new int[resLength];
        /// 相加
        int i = num1Length - 1, j = num2Length - 1;
        int currentIndex = resLength - 1;
        while (i >= 0 && j >= 0) {
            res[currentIndex--] = (num1.charAt(i--) - '0') + (num2.charAt(j--) - '0');
        }
        while (i >= 0) {
            res[currentIndex--] = num1.charAt(i--) - '0';
        }
        while (j >= 0) {
            res[currentIndex--] = num2.charAt(j--) - '0';
        }
        /// 进位
        int curry = 0;
        for (i = resLength - 1; i >= 0; i--) {
            int result = res[i] + curry;
            curry = result / 10;
            res[i] = result % 10;
        }
        /// 去除头部的 0
        i = 0;
        while (i < resLength && res[i] == 0) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        while (i < resLength) {
            sb.append(res[i++]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode415 leetCode415 = new LeetCode415();
//        leetCode415.addStrings("1", "2");
        leetCode415.addStrings("9999", "9");
        leetCode415.addStrings("0", "10");
    }
}
