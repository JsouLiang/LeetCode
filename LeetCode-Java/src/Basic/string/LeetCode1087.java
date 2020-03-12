package Basic.string;

public class LeetCode1087 {
    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int len = Math.min(len1, len2);
        for (int i = len; i >= 1; i--) {
            String divisorString = str1.substring(0, i);
            if (isDivisorOfString(str1, divisorString) && isDivisorOfString(str2, divisorString)) {
                return divisorString;
            }
        }
        return "";
    }

    private boolean isDivisorOfString(String string, String divisorString) {
        int lenOfString = string.length();
        int lenOfDivisor = divisorString.length();
        if (lenOfString % lenOfDivisor != 0) {
            return false;
        }
        int count = lenOfString / lenOfDivisor;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(divisorString);
        }
        return sb.toString().equals(string);
    }

    public static void main(String[] args) {
        LeetCode1087 leetCode1087 = new LeetCode1087();
        leetCode1087.gcdOfStrings("ABCABC", "ABC");
        leetCode1087.gcdOfStrings("ABABAB", "AB");
    }
}
