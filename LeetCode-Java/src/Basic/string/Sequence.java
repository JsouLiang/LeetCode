package Basic.string;

public class Sequence {
    /**
     * 字符串匹配，暴力算法
     */
    public int bruteForce(String text, String pattern) {
        if (text == null || pattern == null || pattern.isEmpty()) {
            return 0;
        }
        /// tPtr 当前匹配 Text 中的下标
        /// pPtr 当前匹配 Patter 中的下标
        int tPtr = 0, pPtr = 0;

        while (pPtr < pattern.length() && tPtr < text.length()) {
            if (text.charAt(tPtr) == pattern.charAt(pPtr)) {
                pPtr++;
                tPtr++;
            } else {
                /// tPtr = tPtr - pPtr + 1;
                tPtr -= pPtr - 1;
                pPtr = 0;
                /// 如果接下来Text 要匹配的字符的位置到 Text 字符串结束位置的字符数 < pattern 串的数量，就不用继续了
                if (tPtr > text.length() - pattern.length()) {
                    break;
                }
            }
        }
        /// pattern 在 text 中找到
        if (pPtr == pattern.length()) {
            if (tPtr - pPtr < text.length()) {
                return tPtr - pPtr;
            }
        }

        return -1;
    }

    public int kmp(String text, String pattern) {
        if (text == null || text.length() == 0 || pattern == null || pattern.length() == 0) {
            return 0;
        }
        int textIndex = 0; int patternIndex = 0;
        final int textLen = text.length(); final int patternLen = pattern.length();
        int[] next = next(pattern);
        while (patternIndex < patternLen && textIndex < textLen) {
            if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {
                patternIndex++; textIndex++;
            } else {
//                textIndex -= patternIndex - 1;
                patternIndex = next[patternIndex];
            }
        }
        return  1;
    }

    private int[] next(String pattern) {
        return new int[]{};
    }

    private int kmpOfDFA(String text, String pattern) {
        int patternLen = pattern.length();
        int[][] dfa = new int[3][patternLen];
        // build DFA from pattern
        dfa[pattern.charAt(0) - 'A'][0] = 1;
        for (int x = 0, j = 1; j < patternLen; j++) {
            System.out.println("x value is " + String.valueOf(x));
            for (int c = 0; c < 3; c++)
                dfa[c][j] = dfa[c][x];                // Copy mismatch cases.
            System.out.println("----------------------");
            printInfo(dfa, patternLen);
            dfa[pattern.charAt(j) - 'A'][j] = j+1;   // Set match case.
            System.out.println("----------------------");
            x = dfa[pattern.charAt(j) - 'A'][x];     // Update restart state.
            printInfo(dfa, patternLen);
            System.out.println("----------------------");
        }
        return 1;
    }

    private void printInfo(int[][]array, int patternLen) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < patternLen; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence();
        int index = sequence.bruteForce("010001010", "1010");
        index = sequence.bruteForce("hello", "ll");

        index = sequence.bruteForce("aaaaa", "bba");
        index = sequence.bruteForce("aaa", "aaaaa");
        sequence.kmpOfDFA("aaa", "ABABAC");
        System.out.println(index);
    }
}
