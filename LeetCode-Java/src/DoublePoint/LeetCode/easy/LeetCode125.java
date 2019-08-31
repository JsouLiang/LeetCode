package SlidingWindowDoublePoint.LeetCode.easy;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class LeetCode125 {
    public static boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        int left = 0, right = s.length() - 1;
        String checkedString = s.toLowerCase();
        while (left < right) {
            char leftCharacter = checkedString.charAt(left++);
            char rightCharacter = checkedString.charAt(right--);
            while (!isNumber(leftCharacter) && !isLetter(leftCharacter)) {
                leftCharacter = checkedString.charAt(left++);
                /// left 走到了 s 结尾都没有合法字符，则表示整串都是非法的字符，此时题意表明是合法回文串
                if (left == s.length()) {
                    return true;
                }
            }
            while (!isNumber(rightCharacter) && !isLetter(rightCharacter) && right >= 0) {
                rightCharacter = checkedString.charAt(right--);
            }

            if (rightCharacter != leftCharacter) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumber(char character) {
        return Character.isDigit(character);
    }

    private static boolean isLetter(char character) {
        return 'a' <= character && character <= 'z' || 'A' <= character && character <= 'Z';
    }

    public static void main(String[] args) {
        boolean isEqual = LeetCode125.isPalindrome("A man, a plan, a canal: Panama");
        isEqual = LeetCode125.isPalindrome("race a car");
        isEqual = LeetCode125.isPalindrome(".,");
        isEqual = LeetCode125.isPalindrome("abba.,");
    }
}
