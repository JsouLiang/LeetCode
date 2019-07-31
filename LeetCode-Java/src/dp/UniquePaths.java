package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DP
 * Created by X-Liang
 * 2017-10-07-17:28
 *
 * @Description:
 * N*M的棋盘上，小兵要从左下角走到右上角，只能向上或者向右走， 问有多少种走法
 */
public class UniquePaths {
    // DP 自底向上
    public int uniquePathsI(int m, int n) {
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            result[i][n - 1] = 1;
        }

        for (int i = 0; i < n; i++) {
            result[m - 1][i] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                result[i][j] = result[i + 1][j] + result[i][j + 1];
            }
        }
        return result[0][0];
    }

    // 递归自顶向下
    private static int[][] results;
    public int uniquePathsII(int m, int n) {
        results = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                results[i][j] = -1;
            }
        }
        return f(m,n);
    }
    int f(int m, int n) {
        if (m == 0 && n == 0) {
            return 0;
        }

        if (m == 1 || n == 1) {
            return 1;
        }
        if (results[m][n] != -1) {
            return results[m][n];
        }
        results[m][n] = f(m - 1, n) + f(m, n - 1);
        return results[m][n];
    }

}

class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int m = Integer.parseInt(line);
            line = in.readLine();
            int n = Integer.parseInt(line);

            int ret = new UniquePaths().uniquePathsI(m, n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
