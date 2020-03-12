package Basic.string;

/**
 * 604. 迭代压缩字符串
 */
public class LeetCode604 {
    private String compressedString;
    private Character preChar;
    private int count = 0;
    private int currentIndex = 0;
    private String originalString;
    private int originStringIndex = 0;
    public LeetCode604(String compressedString) {
        this.compressedString = compressedString;
        /// 一上来展开成原始串，超时
//        originalString = "";
//        StringBuilder sb = new StringBuilder("");
//        char[] chars = compressedString.toCharArray();
//        int index = 0;
//        while (index < chars.length) {
//            char currentChar = chars[index++];
//            int count =  0;
//            while (index < chars.length && isNum(chars[index])) {
//                count = count * 10 + toNum(chars[index]);
//                index++;
//            }
//            while (count > 0) {
//                System.out.println(count);
//                sb.append(currentChar);
//                count--;
//            }
//        }
//        System.out.println(' ');
    }

    public char next() {
//        if (originStringIndex < originalString.length())
//            return originalString.charAt(originStringIndex++);
//        return ' ';
        /// 当前有字符，并且 count > 0
        if (preChar != null && count > 0) {
            count--;
            return preChar;
        }
        /// 还有字符
        if (!hasNext()) {
            return ' ';
        }
        /// char
        char currentChar = compressedString.charAt(currentIndex++);
        /// count
        while (currentIndex < compressedString.length() && isNum(compressedString.charAt(currentIndex))) {
            count = count * 10 + toNum(compressedString.charAt(currentIndex));
            currentIndex++;
        }
        preChar = currentChar;
        count--;
        return preChar;
    }

    private boolean isNum(char currentChar) {
        return '0' <= currentChar && currentChar <= '9';
    }

    private int toNum(char currentChar) {
        return currentChar - '0';
    }

    public boolean hasNext() {
//        return originStringIndex >= originalString.length();
        //// Method 2
        /// 如果当前的 index 已经到达压缩字符串的结尾，并且剩余字符数为0
        return !(currentIndex >= compressedString.length() && (count == 0));
    }

    public static void main(String[] args) {
//        LeetCode604 iterator = new LeetCode604("L1e2t1C1o1d1e1");
//        char val = iterator.next(); // 返回 'L'
//        val = iterator.next(); // 返回 'e'
//        val = iterator.next(); // 返回 'e'
//        val = iterator.next(); // 返回 't'
//        val = iterator.next(); // 返回 'C'
//        val = iterator.next(); // 返回 'o'
//        boolean res = iterator.hasNext(); // 返回 true
//        val = iterator.next(); // 返回 'd'
//        res = iterator.hasNext(); // 返回 true
//        val = iterator.next(); // 返回 'e'
//        res = iterator.hasNext();
//        val = iterator.next(); // 返回 ' '
        LeetCode604 iterator = new LeetCode604("a1234567890b1234567890");
    }
}
