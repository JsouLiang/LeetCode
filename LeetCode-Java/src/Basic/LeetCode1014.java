package Basic;

public class LeetCode1014 {
    public int maxScoreSightseeingPair(int[] A) {
        int score = 0;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                score = Math.max(A[i] + A[j] + i - j, score);
            }
        }
        return score;
    }

    public static void main(String[] args) {
        LeetCode1014 leetCode1014 = new LeetCode1014();
        leetCode1014.maxScoreSightseeingPair(new int[]{8,1,5,2,6});
    }
}
