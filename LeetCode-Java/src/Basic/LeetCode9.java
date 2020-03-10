package Basic;

public class LeetCode9 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        long revered = 0;
        int tem = x;
        while (tem != 0) {
            revered = revered * 10 + tem % 10;
            tem /= 10;
        }
        return revered == x;
    }

    public static void main(String[] args) {
        LeetCode9 leetcode9 = new LeetCode9();
        leetcode9.isPalindrome(121);
    }
}
