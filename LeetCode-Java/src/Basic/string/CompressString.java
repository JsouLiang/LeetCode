package Basic.string;

/**
 * 面试题 01.06. 字符串压缩
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
 * 比如，字符串aabcccccaaa会变为a2b1c5a3。
 * 若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 *
 *  输入："aabcccccaaa"
 *  输出："a2b1c5a3"
 *
 *  输入："abbccd"
 *  输出："abbccd"
 *  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 */
public class CompressString {
    public String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        char[] chars = S.toCharArray();

        int index = 0;
        while (index < chars.length) {
            char currentChar = chars[index++];
            int count = 1;
            while (index < chars.length && chars[index] == currentChar) {
                count++;
                index++;
            }
            sb.append("" + currentChar + String.valueOf(count));
        }
        String compressedString = sb.toString();
        return compressedString.length() < S.length() ? compressedString : S;
    }

    public String compressStringTwoPointer(String S) {
        if (S.length() == 0) {
            return "";
        }
        char[] chars = S.toCharArray();
        char left = chars[0];
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < chars.length; i++) {
            char right = chars[i];
            if (right == left) {
                count++;
            } else {
                sb.append(left).append(count);
                left = chars[i];
                count = 1;
            }
        }
        sb.append(left).append(count);
        String compressedString = sb.toString();
        return compressedString.length() < S.length() ? compressedString : S;
    }

    public String compressStringTwoPointerII(String S) {
        char[] chars = S.toCharArray();
        int left = 0;
        int count = 1;
        int slen = S.length();
        StringBuilder sb = new StringBuilder();
        while (left < slen) {
            int right = left + 1;
            while (right < slen && chars[right] == chars[left]) {
                count++;
                right++;
            }
            sb.append(chars[left]).append(count);
            left = right;
            count = 1;
        }
        String compressedString = sb.toString();
        return compressedString.length() < S.length() ? compressedString : S;
    }

    public static void main(String[] args) {
        CompressString compressString = new CompressString();
        String res = compressString.compressStringTwoPointerII("aabcccccaaa");
        res = compressString.compressStringTwoPointerII("abbccd");
        res = compressString.compressStringTwoPointerII("");
    }
}
