package Stack;

public class LeetCode1111 {
    public int[] maxDepthAfterSplit(String seq) {
        int stackDepth = 0;
        int[] depths = new int[seq.length()];
        int[] res = new int[seq.length()];
        int index = 0;
        ///  "( ( ) ( ( ) ) ( ) )"
        ///   1 2 2 2 3 3 2 2 2 1
        for (Character character : seq.toCharArray()) {
            if (character == '(') {
                stackDepth++;
//                depths[index++] = stackDepth;
                res[index++] = stackDepth % 2;
            } else {
//                depths[index++] = stackDepth;
                res[index++] = stackDepth % 2;
                stackDepth--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode1111 leetCode1111 = new LeetCode1111();
        leetCode1111.maxDepthAfterSplit("(()(())())");
    }
}
