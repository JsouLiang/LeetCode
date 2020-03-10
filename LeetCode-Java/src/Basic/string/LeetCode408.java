package Basic.string;

public class LeetCode408 {
    public boolean validWordAbbreviation(String word, String abbr) {
        int wordIndex = 0;
        for (int i = 0; i < abbr.length();) {
            /// 如果当前abbr 的字符不是数字
            if (!isNumber(abbr.charAt(i))) {
                /// abbr 还有值，但是当前取的 wordIndex 已经超过了 word 长度
                if (wordIndex >= word.length()) {
                    return false;
                }
                char wordChar = word.charAt(wordIndex);
                char abbrChar = abbr.charAt(i);
                if (wordChar != abbrChar) {
                    return false;
                }
                wordIndex++;
                i++;
            } else {
                int numberIndex = i;
                int count = 0;
                /// 前导零过滤
                if (count == 0 && numOfChar(abbr.charAt(numberIndex)) == 0) {
                    return false;
                }
                while (numberIndex < abbr.length() && isNumber(abbr.charAt(numberIndex))) {
                    count = count * 10 + numOfChar(abbr.charAt(numberIndex));
                    numberIndex++;
                }
                i = numberIndex;
                wordIndex += count;
                /// 如果计算的 wordIndex 超过了 word 的长度那么 false
                if (wordIndex > word.length()) {
                    return false;
                }
            }
        }
        /// word 全部被 check 完成
        /// case: word: hi, abbr: 1
        return wordIndex == word.length();
    }

    private boolean isNumber(char currentChar) {
        return currentChar >= '0' && currentChar <= '9';
    }

    private int numOfChar(char currentChar) {
        return currentChar - '0';
    }

    public static void main(String[] args) {
        LeetCode408 leetCode408 = new LeetCode408();
        leetCode408.validWordAbbreviation("a", "2");
        leetCode408.validWordAbbreviation("internationalization", "i5a11o1");
//        leetCode408.validWordAbbreviation("apple", "a2e");
//        leetCode408.validWordAbbreviation("a", "01");
        leetCode408.validWordAbbreviation("hi", "2h");
    }
}
