package DFS;

import java.util.ArrayList;
import java.util.List;

public class LeetCode293 {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String left = "";
                if (i -  1 >= 0) {
                    /// substring: [left, right);
                    left = s.substring(0, i);
                }
                String right = "";
                if (i + 2 < s.length()) {
                    right = s.substring(i + 2);
                }
                res.add(left + "--" + right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode293 leetCode293 = new LeetCode293();
//        leetCode293.generatePossibleNextMoves("++++");
//        leetCode293.generatePossibleNextMoves("+++");
//        leetCode293.generatePossibleNextMoves("+");
        leetCode293.generatePossibleNextMoves("+--+");
    }
}
