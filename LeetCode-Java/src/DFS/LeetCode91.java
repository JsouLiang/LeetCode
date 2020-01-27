package DFS;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 */
public class LeetCode91 {
    private int res;
    public int numDecodings(String s) {
        res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        dfs(s, 0);
        return res;
    }

    private void dfs(String s, int from) {
        if (from > s.length()) {
            return;
        }
        if (from == s.length()) {
            res++;
            return;
        }

        for (int i = 1; i <= 2; i++) {
            int endIndex = Math.min(from + i, s.length());
            String subStr = s.substring(from, endIndex);
            if (check(subStr)) {
                dfs(s, from + i);
            }
        }
    }

    private boolean check(String s) {
        if (s.length() == 0) {
            return false;
        }
        if (s.startsWith("0")) {
            return false;
        }
        if (s.length() == 2 && s.charAt(0) > '2') {
            return false;
        }
        int parsedValue = Integer.parseInt(s);
        return parsedValue >= 1 && parsedValue <= 26;
    }

    public static void main(String[] args) {
        LeetCode91 leetCode91 = new LeetCode91();
        leetCode91.numDecodings("12");
        leetCode91.numDecodings("226");
        leetCode91.numDecodings("01");
        leetCode91.numDecodings("6065812287883668764831544958683283296479682877898293612168136334983851946827579555449329483852397155");
            leetCode91.numDecodings("9317949759856497357254398763219839323723136763131916377913495416692666785978758414629119614215967159");
            leetCode91.numDecodings("1787897759966261825913315262377298132516969578441236833255596967132573482281598412163216914566534565");
    }
}
