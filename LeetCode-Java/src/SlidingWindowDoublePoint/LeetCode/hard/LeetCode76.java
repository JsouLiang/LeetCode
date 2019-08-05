package SlidingWindowDoublePoint.LeetCode.hard;

import java.util.HashMap;
import java.util.Map;

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
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();

        // Left and Right pointer
        int l = 0, r = 0;

        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;

        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and co***act the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }

            // Keep expanding the window once we are done co***acting.
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    public static void main(String[] args) {
        LeetCode76.minWindow("kgfidhktkjhlkbgjkylgdracfzjduycghkomrbfbkoowqwgaurizliesjnveoxmvjdjaepdqftmvsuyoogobrutahogxnvuxyezevfuaaiyufwjtezuxtpycfgasburzytdvazwakuxpsiiyhewctwgycgsgdkhdfnzfmvhwrellmvjvzfzsdgqgolorxvxciwjxtqvmxhxlcijeqiytqrzfcpyzlvbvrksmcoybxxpbgyfwgepzvrezgcytabptnjgpxgtweiykgfiolxniqthzwfswihpvtxlseepkopwuueiidyquratphnnqxflqcyiiezssoomlsxtyxlsolngtctjzywrbvajbzeuqsiblhwlehfvtubmwuxyvvpwsrhutlojgwktegekpjfidgwzdvxyrpwjgfdzttizquswcwgshockuzlzulznavzgdegwyovqlpmnluhsikeflpghagvcbujeapcyfxosmcizzpthbzompvurbrwenflnwnmdncwbfebevwnzwclnzhgcycglhtbfjnjwrfxwlacixqhvuvivcoxdrfqazrgigrgywdwjgztfrbanwiiayhdrmuunlcxstdsrjoapntugwutuedvemyyzusogumanpueyigpybjeyfasjfpqsqotkgjqaxspnmvnxbfvcobcudxflmvfcjanrjfthaiwofllgqglhkndpmiazgfdrfsjracyanwqsjcbakmjubmmowmpeuuwznfspjsryohtyjuawglsjxezvroallymafhpozgpqpiqzcsxkdptcutxnjzawxmwctltvtiljsbkuthgwwbyswxfgzfewubbpowkigvtywdupmankbndyligkqkiknjzchkmnfflekfvyhlijynjlwrxodgyrrxvzjhoroavahsapdiacwjpucnifviyohtprceksefunzucdfchbnwxplhxgpvxwrmpvqzowgimgdolirslgqkyc", "tjcwallfkarlrvfxchdqqtiutvfpoovjxzgxmtextvintpmvypnplyletrwhftreszdhshenfocadoxegkvrigxbzvleqckjdnsvvwkckncpdztjloauxaxwvibmmlxpbpmwnzaxmcopdiboydkvdisbqvpfiowjfjhsihrwlfnopodosnjxxdyqynvhbrqgcyamhrktzyhoomcgcoezrerssozvipekpezxyjqxjzlymqeqgkrzpjrjxqgfimszrtwrcoqmqbketqubbnbswsbwljdvwxupqtgtjwhzztdvjzwmnzglsjjftnapkwedpmybkfalyggjffyueegyopfhefyreeuvsswczznxfwimbghhlpgbelklticxoyugsrkrqzqxvyjyhqiufqvmdfzwpdvddqlvjvozwewuehslyahfsctwjsuyxsdaiqvtnlskpqewxyjxzrfttypftkdqcjtmzofnczxrrbpqzboastuntlsovyhxhalgqqtsrsmivbzxcnzwivkdhesccbcjbnsrelmvgygbbfyguyeetohavbfxehjfwbzconaulgwolwwhwblsruumyzmcivkfylhmyhjlphbadyjczwusrohrotvyqfdosncqwldmsfoyfyaeuuynifeyyaxqhcgaplsmywardorimtohnmuxsbysdxlkzrmrehfdffwitnqigepvslumoshrpserlsiqzpteupmneexkkmhdabrquyilqocegmuibpmxgbnkhkwszdxzeorapbmhpqlydhggyueevrqfdmxcrwdwmvwdwklmbykeismgmqnkjdpnqopjmtfyqeemopapnmvveierardkuuzmiqwwldwbhaowpqnfdjchrgarxfduzeihvedikakraapsqdxtmzdfidyfjebiiksfqxoazaucajusotmcuphcuikfmlqwxkcohsqhsmluyfmmaypupyzmgjtuwjrutvkdncmhpxb");
    }
}
