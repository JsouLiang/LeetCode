package Basic;

import java.util.ArrayList;
import java.util.List;

public class LeetCode67 {
    public String addBinary(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        int count = Math.min(aChars.length, bChars.length);
        int aIndex = aChars.length - 1, bIndex = bChars.length - 1;
        List<Integer> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int carry = 0;
        while (count > 0) {
            int aVal = aChars[aIndex--] - '0';
            int bVal = bChars[bIndex--] - '0';
            int val = aVal + bVal + carry;
            carry = val / 2;
            sb.insert(0, val % 2);
            count--;
        }
        while (aIndex >= 0) {
            int aVal = aChars[aIndex--] - '0';
            int val = aVal + carry;
            carry = val / 2;
            sb.insert(0, val % 2);

        }

        while (bIndex >= 0) {
            int bVal = bChars[bIndex--] - '0';
            int val = bVal + carry;
            carry = val / 2;
            sb.insert(0, val % 2);

        }
        if (carry != 0) {
            sb.insert(0, 1);
        }


        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode67 leetCode67 = new LeetCode67();
        leetCode67.addBinary("11", "1");
        leetCode67.addBinary("1010", "1011");
    }
}
