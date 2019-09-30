package Basic.string;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @implNote 大树相乘问题
 * 1. 先计算每一位的乘积
 * 2. 统一计算进位
 */
public class LeetCode43 {

    public String multiply(String num1, String num2) {
        /// java 字符串判断相等用 equals
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        final int num1Length = num1.length();
        final int num2Length = num2.length();
        /// n位 * m位 得到的结果长度 <= n + m
        final int resLength = num1Length + num2Length;
        int[] multiplyRes = new int[resLength];
        /// 计算每一位的值
        /// 从右往左计算
        for (int i = num1Length - 1; i >= 0; i--) {
            for (int j = num2Length - 1; j >= 0; j--) {
                multiplyRes[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        /// 对每一位处理进位
        int curry = 0;
        for (int i = resLength - 1; i >= 0; i--) {
            int value = multiplyRes[i] + curry;
            curry = value / 10;
            multiplyRes[i] = value % 10;
        }
        /// 去除数组前面的 0
        int i = 0;
        while (i < resLength && multiplyRes[i] == 0){
            i++;
        }
        StringBuilder res = new StringBuilder();
        while (i < resLength)  {
            res.append(multiplyRes[i++]);
        }
        return res.toString();
    }

    public String multiplyII(String num1, String num2) {
        if (num1 == "0" || num2 == "0") {
            return "0";
        }
        /**
         * n长度的数乘以m长度的数，那么乘积肯定是n+m或者n+m-1的长度
         */
        final int num1Length = num1.length();
        final int num2Length = num2.length();
        int numsLength = num1Length + num2Length;
        int[] multiRes = new int[numsLength];

        /**
         *    num2
         *  x num1
         *  -------
         */
        int carry = 0; int i; int j;
        for (i = num1Length - 1; i >= 0; i--) {
            carry = 0;
            for (j = num2Length - 1; j >= 0; j--) {
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';
                /// 结果值当前的乘机 + 进位
                int res = a * b + carry + multiRes[i + j + 1];
                /// 当前位的值
                int value = (res) % 10;
                /// 进位
                carry = res / 10;
                multiRes[i + j + 1] = value;
            }
            multiRes[i + j + 1] = carry;
        }
        /// 处理前面的 0
        i = 0;
        while (i < numsLength && multiRes[i] == 0) {
            i++;
        }
        StringBuilder res = new StringBuilder();
        while (i < numsLength)  {
            res.append(multiRes[i++]);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        LeetCode43 leetCode43 = new LeetCode43();
        leetCode43.multiply("0", "0");
        leetCode43.multiply("123", "119");

        leetCode43.multiply("9", "99");
    }
}
