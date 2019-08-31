package SlidingWindowDoublePoint.LeetCode.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class LeetCode76 {
    public static String minWindow(String s, String t) {
        final char[] sChars = s.toCharArray();
        final char[] tChars = t.toCharArray();
        Map<Character, Integer> targetMap = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (Character c : tChars) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        //
        int matched = 0;
        int left = 0; int right = 0;
        int minStringStart = 0; int minStringLength = 0xFFFFFF;
        while (right < s.length()) {
            char rightChar = sChars[right++];
            window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);

            if (targetMap.containsKey(rightChar) && window.get(rightChar).intValue() == targetMap.get(rightChar).intValue()) {
                // 字符 rightChar 的出现次数已经满足了
                matched++;
            }
            /// 右移 left，缩小范围
            while (left <= right && matched == targetMap.size()) {
                if (right - left < minStringLength) {
                    minStringStart = left;
                    minStringLength = right - left;
                }
                char leftChar = sChars[left++];
                /// 从窗口中移除
                window.put(leftChar, window.get(leftChar) - 1);
                /// 匹配的字符数是否要降低
                /// 1. target 串中包含 leftChar
                /// 2. 窗口中 leftChar 的数量已经小于 target 中的数量
                if (targetMap.containsKey(leftChar) && window.get(leftChar).intValue() < targetMap.get(leftChar).intValue()) {
                    /// 窗口中 leftChar 的个数与 目标串中的个数不一致，不 match
                    matched--;
                }
            }
        }
        String res = minStringLength == 0xFFFFFF ? "" : s.substring(minStringStart,  minStringStart + minStringLength);
        return res;
    }

    public static String solute(String s, String t) {
        Map<Character, Integer> characterCount = new HashMap<>();
        final char[] tChars = t.toCharArray();
        final char[] sChars = s.toCharArray();
        /// 将 t 字符串中需要的字符放到 Map 中 : {'c': n}
        /// n 大于0 表示还需要 n 个, c 字符
        /// n 小于0 表示多出了 n 个, c 字符
        Map<Character, Integer> targetMap = new HashMap<>();
        for (Character c : tChars) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        /// 匹配的字符个数
        int matchedCharacterCount = 0;
        //
        int totalWaitingMatchCharacterCount = targetMap.size();
        String result = "";
        /// left 和 right 维护一个区间
        /// 开始 right 先往前扫描字符，知道满足 t串中字符的需求
        /// 当满足需求后，向右移动 left，缩小窗口（left 和 right 之间的距离）
        for (int right = 0, left = 0; right < sChars.length; right++) {
            /// 获取右指针指向的值，右指针负责从源字符串中读取字符
            final Integer currentCharacterCount = targetMap.getOrDefault(sChars[right], 0);
            /// 如果当前右指针指向的字符个数 恰好为1，则说明到当前这个右指针指向的值为止，该字符已经匹配完成
            if (currentCharacterCount == 1) {
                matchedCharacterCount++;
            }
            /// 将该右指针指向的字符数减一，表示：需求量减一
            /// 思考:
            /// 1.如果对于一个没有在 target 字符串中的字符来说，该值会是负数
            /// 2.如果某个字符读取的个数已经 > t 串中需求的量，该值也为负数
            targetMap.put(sChars[right], currentCharacterCount - 1);
            /// 当前 left 和 right 之间的字符已经满足 t 串需要的字符
            if (matchedCharacterCount == totalWaitingMatchCharacterCount) {
                /// 缩小 left
                Integer leftCharacterCount = targetMap.getOrDefault(sChars[left], 0);
                /// 如果 left 指向的字符数是 < 0, 那么
                /// 1. 该字符不是组成 t 串的字符，抛弃即可
                /// 2. 该字符是组成 t 的字符
                /// 每从 left ~ right 区间中抛出一个字符时，表示该字符的需求量 + 1
                /// 而 left 的终止条件就是当前 left 指向的字符数 >= 0
                while (leftCharacterCount < 0) {
                    targetMap.put(sChars[left++], leftCharacterCount + 1);
                    if (left < s.length()) {
                        leftCharacterCount = targetMap.getOrDefault(sChars[left], 0);
                    } else {
                        break;
                    }

                }

                if (result.isEmpty() || result.length() > right - left + 1) {
                    result = s.substring(left, right + 1);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String result = LeetCode76.minWindow("kgfidhktkjhlkbgjkylgdracfzjduycghkomrbfbkoowqwgaurizliesjnveoxmvjdjaepdqftmvsuyoogobrutahogxnvuxyezevfuaaiyufwjtezuxtpycfgasburzytdvazwakuxpsiiyhewctwgycgsgdkhdfnzfmvhwrellmvjvzfzsdgqgolorxvxciwjxtqvmxhxlcijeqiytqrzfcpyzlvbvrksmcoybxxpbgyfwgepzvrezgcytabptnjgpxgtweiykgfiolxniqthzwfswihpvtxlseepkopwuueiidyquratphnnqxflqcyiiezssoomlsxtyxlsolngtctjzywrbvajbzeuqsiblhwlehfvtubmwuxyvvpwsrhutlojgwktegekpjfidgwzdvxyrpwjgfdzttizquswcwgshockuzlzulznavzgdegwyovqlpmnluhsikeflpghagvcbujeapcyfxosmcizzpthbzompvurbrwenflnwnmdncwbfebevwnzwclnzhgcycglhtbfjnjwrfxwlacixqhvuvivcoxdrfqazrgigrgywdwjgztfrbanwiiayhdrmuunlcxstdsrjoapntugwutuedvemyyzusogumanpueyigpybjeyfasjfpqsqotkgjqaxspnmvnxbfvcobcudxflmvfcjanrjfthaiwofllgqglhkndpmiazgfdrfsjracyanwqsjcbakmjubmmowmpeuuwznfspjsryohtyjuawglsjxezvroallymafhpozgpqpiqzcsxkdptcutxnjzawxmwctltvtiljsbkuthgwwbyswxfgzfewubbpowkigvtywdupmankbndyligkqkiknjzchkmnfflekfvyhlijynjlwrxodgyrrxvzjhoroavahsapdiacwjpucnifviyohtprceksefunzucdfchbnwxplhxgpvxwrmpvqzowgimgdolirslgqkyc", "tjcwallfkarlrvfxchdqqtiutvfpoovjxzgxmtextvintpmvypnplyletrwhftreszdhshenfocadoxegkvrigxbzvleqckjdnsvvwkckncpdztjloauxaxwvibmmlxpbpmwnzaxmcopdiboydkvdisbqvpfiowjfjhsihrwlfnopodosnjxxdyqynvhbrqgcyamhrktzyhoomcgcoezrerssozvipekpezxyjqxjzlymqeqgkrzpjrjxqgfimszrtwrcoqmqbketqubbnbswsbwljdvwxupqtgtjwhzztdvjzwmnzglsjjftnapkwedpmybkfalyggjffyueegyopfhefyreeuvsswczznxfwimbghhlpgbelklticxoyugsrkrqzqxvyjyhqiufqvmdfzwpdvddqlvjvozwewuehslyahfsctwjsuyxsdaiqvtnlskpqewxyjxzrfttypftkdqcjtmzofnczxrrbpqzboastuntlsovyhxhalgqqtsrsmivbzxcnzwivkdhesccbcjbnsrelmvgygbbfyguyeetohavbfxehjfwbzconaulgwolwwhwblsruumyzmcivkfylhmyhjlphbadyjczwusrohrotvyqfdosncqwldmsfoyfyaeuuynifeyyaxqhcgaplsmywardorimtohnmuxsbysdxlkzrmrehfdffwitnqigepvslumoshrpserlsiqzpteupmneexkkmhdabrquyilqocegmuibpmxgbnkhkwszdxzeorapbmhpqlydhggyueevrqfdmxcrwdwmvwdwklmbykeismgmqnkjdpnqopjmtfyqeemopapnmvveierardkuuzmiqwwldwbhaowpqnfdjchrgarxfduzeihvedikakraapsqdxtmzdfidyfjebiiksfqxoazaucajusotmcuphcuikfmlqwxkcohsqhsmluyfmmaypupyzmgjtuwjrutvkdncmhpxb");
        result = LeetCode76.solute("ABBCABC", "ABC");
        result = LeetCode76.solute("abcabdebac", "cda");
        result = LeetCode76.solute("b", "a");
    }
}
