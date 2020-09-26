package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode402 {
    String sourceStr;
    public String removeKdigits(String num, int k) {
        sourceStr = num;
        String res = remove(num.toCharArray(), 0, new HashSet<>(), k);
        return res;
    }

    private String remove(char[] sb, int currentIndex, Set<Integer> deletedIndex, int k) {
        if (currentIndex == sb.length - 1 || deletedIndex.size() == k) {
            StringBuilder tempSb = new StringBuilder();
            for (int i = 0; i < sb.length; i++) {
                if (deletedIndex.contains(i)) { continue; }
                tempSb.append(sb[i]);
            }
            return tempSb.toString();
        }
        /// 删除 currentIndex 元素
        deletedIndex.add(currentIndex);
        int val1 = Integer.parseInt(remove(sb, currentIndex + 1, deletedIndex, k));
        deletedIndex.remove(currentIndex);
        int val2 = Integer.parseInt(remove(sb, currentIndex + 1, deletedIndex, k));
        return val1 > val2 ? String.valueOf(val2) : String.valueOf(val1);
    }

    public static void main(String[] args) {
        LeetCode402 leetCode402 = new LeetCode402();
        leetCode402.removeKdigits("10200", 1);
    }
}
