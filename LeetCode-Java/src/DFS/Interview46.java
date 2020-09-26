package DFS;

public class Interview46 {
    public int translateNum(int num) {
        return helper(num);
    }

    private int helper(int num) {
        if (num < 10) {
            return 1;
        }

        if (num % 100 <= 25 && num % 100 >= 10) {
            return helper(num/10) + helper(num/100);
        } else {
            return helper(num / 10);
        }
    }
}
