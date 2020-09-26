package DFS;

/**
 * 44. 通配符匹配
 *
 */
public class LeetCode44 {
    private char[] sChars;
    private char[] pChars;
    private int sLen;
    private int pLen;
    Boolean[][] visited;
    public boolean isMatch(String s, String p) {
        sChars = s.toCharArray();
        pChars = p.toCharArray();
        sLen = sChars.length; pLen = pChars.length;
        visited = new Boolean[sLen + 1][pLen + 1];
        return isMatch(0, 0);
    }

    private boolean isMatch(int sIndex, int pIndex) {
        if (visited[sIndex][pIndex] != null) {
            return visited[sIndex][pIndex];
        }
        if (pIndex == pLen) {
            return sIndex == sLen;
        }
        boolean isFirstMatch = sIndex < sLen && sChars[sIndex] == pChars[pIndex] || pChars[pIndex] == '?';
        if (pChars[pIndex] == '*') {
            boolean sForward = sIndex < sLen && isMatch(sIndex + 1, pIndex);
            boolean pForward = isMatch(sIndex, pIndex + 1);
            visited[sIndex][pIndex] = sForward || pForward;
        } else {
            visited[sIndex][pIndex] = isFirstMatch && sIndex < sLen && isMatch(sIndex + 1, pIndex + 1);
        }
        return visited[sIndex][pIndex];
    }

    public static void main(String[] args) {
        LeetCode44 leetCode44 = new LeetCode44();
//        boolean res = leetCode44.isMatch("abbabbbaabaaabbbbbabbabbabbbabbaaabbbababbabaaabbab", "*aabb***aa**a******aa*");
        boolean res = leetCode44.isMatch("aaaaaa", "*aaa*aa");
        res = leetCode44.isMatch("mississippi", "m??*ss*?i*pi");
        res =leetCode44.isMatch("aa", "*");
        res =leetCode44.isMatch("cb", "?a");
        res =leetCode44.isMatch("cb", "?b");
        res =leetCode44.isMatch("cbcbbb", "*b");
        System.out.println(res);
    }
}
