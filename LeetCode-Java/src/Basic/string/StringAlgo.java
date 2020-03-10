package Basic.string;

import java.util.List;

public class StringAlgo {
    /***
     * URL 化。编写一种方法，将字符串中的空格全部替换为 %20。
     * 假定该字符串尾部有足够 的空间存放新增字符，并且知道字符串的“真实”长度。
     * 示例： 输入："Mr John Smith ", 13
     * 输出："Mr%20John%20Smith"
     */
    static char[] parseString(char[] targetStr, int length) {
        int spaceCount = 0;
        for (int i = 0; i < length; i++) {
            if (targetStr[i] == ' ') {
                spaceCount++;
            }
        }
        int rightPoint = length + spaceCount * 2 - 1;

        /// [Mr John Smith]
        //  [Mr John Smith  ]
        //                 ^
        //  [Mr John   Smith]

        //  [Mr John Smith  ]
        //          ^
        // 因为后面有足够的的空间，从后往前开始遍历
        // 又因为新的差点与串长度差点就是多出的字符数，所以从后往前不会将原数据覆盖
        for (int i = length - 1; i >= 0; i--) {
            if (targetStr[i] != ' ') {
                targetStr[rightPoint--] = targetStr[i];
            } else {
                targetStr[rightPoint--] = '0';
                targetStr[rightPoint--] = '2';
                targetStr[rightPoint--] = '%';
            }
        }
        String res =  String.valueOf(targetStr);
        return targetStr;
    }

    public static void reverseString(char[] s) {
//        helper(s, 0, s.length - 1);
        int left = 0, right = s.length - 1;
        while (left <= right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }

    private static void helper(char[] s, int left, int right) {
        if (left >= right) {
            return;
        }
        helper(s,  left + 1, right - 1);
        swap(s, left, right);
    }

    private static void swap(char[] s, int left, int right) {
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
    }

    public static void main(String[] args) {
        StringAlgo.parseString("AB CDEF GHIKL    ".toCharArray(), 13);
        char[] hello = new char[]{'h','e','l','l','o'};
        StringAlgo.reverseString(hello);
        System.out.println(hello);
    }
}
