package Basic.string;

/**
 * 186. 翻转字符串里的单词 II
 */
public class LeetCode186 {
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        int left = 0; int right = 0;
        while (right <= s.length) {
            if (right == s.length || s[right] == ' ') {
                reverse(s, left, right - 1);
                right++;
                left = right;
            } else {
                right++;
            }
        }
    }

    private void reverse(char[] s, int left, int right) {
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++; right--;
        }
    }

    public static void main(String[] args) {
        LeetCode186 leetCode186 = new LeetCode186();
        leetCode186.reverseWords(new char[]{'t','h','e', ' ','s','k','y',' ','i','s',' ','b','l','u','e'});
    }
}
