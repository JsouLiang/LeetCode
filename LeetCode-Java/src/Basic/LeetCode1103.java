package Basic;

public class LeetCode1103 {
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        if (candies == 0 || num_people == 0) {
            return res;
        }
        int i = 0;
        int count = 1;
        while (i < num_people) {
            res[i] += Math.min(count, candies);
            i++;
            candies -= count;
            count++;
            if (candies <= 0) {
                break;
            }
            if (i == num_people) {
                i = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode1103 leetCode1103 = new LeetCode1103();
        leetCode1103.distributeCandies(7, 4);
        leetCode1103.distributeCandies(10, 3);
    }
}
