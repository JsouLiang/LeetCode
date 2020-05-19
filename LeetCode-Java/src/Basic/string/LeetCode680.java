package Basic.string;

public class LeetCode680 {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char leftC = s.charAt(left);
            char rightC = s.charAt(right);
            if (leftC == rightC) {
                left++; right--;
            } else {
                boolean deleteLeft = isPalindrome(s, left+1, right);
                boolean deleteRight = isPalindrome(s, left, right - 1);
                return deleteLeft || deleteRight;
            }
        }
        return true;
    }

    boolean isPalindrome(String s, int left, int right) {
        for (; left < right; left++, right--) {
            char leftC = s.charAt(left);
            char rightC = s.charAt(right);
            if (leftC != rightC) {
                break;
            }
        }
        return left >= right;
    }

    public static void main(String[] args) {
        LeetCode680 leetCode680 = new LeetCode680();
        leetCode680.validPalindrome("abc");
    }
}
