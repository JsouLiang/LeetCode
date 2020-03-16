package Basic.string;

/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 */
public class LeetCode151 {
    public String reverseWords(String s) {
        String[] ts = s.split(" ");
        char[] sChar = s.toCharArray();
        String targetString = "";
        /// 将多余空格删掉
        for (Character character: sChar) {
            if (character != ' ') {
                targetString += character;
            } else {
                int targetStringLen = targetString.length();
                if (targetStringLen > 0) {
                    char prevChar = targetString.charAt(targetStringLen - 1);
                    if (prevChar == ' ') {
                        continue;
                    } else {
                        targetString += character;
                    }
                }
            }
        }
        String[] subStrings = targetString.split(" ");
        targetString = "";
        for (int i = subStrings.length - 1; i >= 0; i--) {
            targetString += subStrings[i];
            targetString += " ";
        }
        targetString = targetString.substring(0, targetString.length() - 1);
        return targetString;
    }

    public static void main(String[] args) {
        LeetCode151 leetCode151 = new LeetCode151();
        leetCode151.reverseWords("   hello   word   hi!  ");
    }

}
