package Stack;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

public class StackTests {
    @Ignore
    @Test
    /**
     * 856. 括号的分数
     */
    public void scoreOfParenthesesTest() {
        /**
         * 1. 使用栈实现
         */
        int result = ScoreOfParentheses.scoreOfParenthesesUsingStack("(()(()))");
        assertEquals(result, 6);
        result = ScoreOfParentheses.scoreOfParenthesesUsingStack("()");
        assertEquals(result, 1);
        result = ScoreOfParentheses.scoreOfParenthesesUsingStack("(())");
        assertEquals(result, 2);
        result = ScoreOfParentheses.scoreOfParenthesesUsingStack("()()");
        assertEquals(result, 2);
        result = ScoreOfParentheses.scoreOfParenthesesUsingStack("(()(()()))");
        assertEquals(result, 10);
        result = ScoreOfParentheses.scoreOfParenthesesUsingStack("(((()(()()))))");
        assertEquals(result, 40);
        result = ScoreOfParentheses.scoreOfParenthesesUsingStack("()(())()(((()(()())))(((())(()))()))(((())))");
        assertEquals(result, 88);
    }

}
