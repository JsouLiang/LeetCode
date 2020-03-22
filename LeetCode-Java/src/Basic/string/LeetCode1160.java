package Basic.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode1160 {
    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> set = new HashMap<>();
        char[] characters = chars.toCharArray();
        for (Character character : characters) {
            set.put(character, set.get(character) == null ? 1 : set.get(character) + 1);
        }
        int count = 0;
        for (String word : words) {
            char[] wordChars = word.toCharArray();
            boolean have = true;
            Map<Character, Integer> currentChars = new HashMap<>(set);
            for (Character character : wordChars) {
                /// 如果使用了非单词表中的字符或者之前已经使用过一次了
                if (currentChars.get(character) == null || currentChars.get(character) == 0) {
                    have = false;
                    break;
                } else {
                    currentChars.put(character, currentChars.get(character) - 1);
                }

            }
            if (have) {
                count += word.length();
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode1160 leetCode1160 = new LeetCode1160();
        String[] tests = {"dyiclysmffuhibgfvapygkorkqllqlvokosagyelotobicwcmebnpznjbirzrzsrtzjxhsfpiwyfhzyonmuabtlwin","ndqeyhhcquplmznwslewjzuyfgklssvkqxmqjpwhrshycmvrb","ulrrbpspyudncdlbkxkrqpivfftrggemkpyjl","boygirdlggnh","xmqohbyqwagkjzpyawsydmdaattthmuvjbzwpyopyafphx","nulvimegcsiwvhwuiyednoxpugfeimnnyeoczuzxgxbqjvegcxeqnjbwnbvowastqhojepisusvsidhqmszbrnynkyop","hiefuovybkpgzygprmndrkyspoiyapdwkxebgsmodhzpx","juldqdzeskpffaoqcyyxiqqowsalqumddcufhouhrskozhlmobiwzxnhdkidr","lnnvsdcrvzfmrvurucrzlfyigcycffpiuoo","oxgaskztzroxuntiwlfyufddl","tfspedteabxatkaypitjfkhkkigdwdkctqbczcugripkgcyfezpuklfqfcsccboarbfbjfrkxp","qnagrpfzlyrouolqquytwnwnsqnmuzphne","eeilfdaookieawrrbvtnqfzcricvhpiv","sisvsjzyrbdsjcwwygdnxcjhzhsxhpceqz","yhouqhjevqxtecomahbwoptzlkyvjexhzcbccusbjjdgcfzlkoqwiwue","hwxxighzvceaplsycajkhynkhzkwkouszwaiuzqcleyflqrxgjsvlegvupzqijbornbfwpefhxekgpuvgiyeudhncv","cpwcjwgbcquirnsazumgjjcltitmeyfaudbnbqhflvecjsupjmgwfbjo","teyygdmmyadppuopvqdodaczob","qaeowuwqsqffvibrtxnjnzvzuuonrkwpysyxvkijemmpdmtnqxwekbpfzs","qqxpxpmemkldghbmbyxpkwgkaykaerhmwwjonrhcsubchs"};
        String s = "usdruypficfbpfbivlrhutcgvyjenlxzeovdyjtgvvfdjzcmikjraspdfp";
        leetCode1160.countCharacters(tests, s);

        String[] tests2 = {"hello","world","leetcode"};
        s = "welldonehoneyr";
        leetCode1160.countCharacters(tests2, s);
    }
}
