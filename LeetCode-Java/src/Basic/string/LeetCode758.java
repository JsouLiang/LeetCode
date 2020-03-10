package Basic.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeetCode758 {
    class _Matched {
        int start;
        int end;

        public _Matched(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
    public String boldWords(String[] words, String S) {
        List<_Matched> matchedIndexs = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String key = words[i];
            List<_Matched> matcheds = findSubString(S, key);
            matchedIndexs.addAll(matcheds);
        }
        if (matchedIndexs.size() > 0) {
            /// 对区间按照开始位置进行排序，开始位置相同时，按照结束位置有小到大排序
            matchedIndexs.sort(new Comparator<_Matched>() {
                @Override
                public int compare(_Matched o1, _Matched o2) {
                    if (o1.start != o2.start) {
                        return o1.start - o2.start;
                    }
                    return o1.end - o2.end;
                }
            });
            /// 合并区间
            List<_Matched> margedMatcheds = new ArrayList<>();
            margedMatcheds.add(new _Matched(matchedIndexs.get(0).start, matchedIndexs.get(0).end));
            _Matched lastMatched = margedMatcheds.get(0);

            for (int i = 1; i < matchedIndexs.size(); i++) {
                _Matched currentMatched = matchedIndexs.get(i);
                if (currentMatched.start <= lastMatched.end) {
                    if (currentMatched.end > lastMatched.end) {
                        lastMatched.end = currentMatched.end;
                    }
                } else {
                    lastMatched = new _Matched(currentMatched.start, currentMatched.end);
                    margedMatcheds.add(lastMatched);
                }
            }
            /// 生成结果字符串
            StringBuilder stringBuilder = new StringBuilder();
            int lastIndex = 0;
            for (int i = 0; i < margedMatcheds.size(); i++) {
                _Matched currentMatch = margedMatcheds.get(i);
                stringBuilder.append(S.substring(lastIndex, currentMatch.start));
                stringBuilder.append("<b>");
                stringBuilder.append(S.substring(currentMatch.start, currentMatch.end));
                stringBuilder.append("</b>");
                lastIndex = currentMatch.end;
            }
            if (lastIndex < S.length()) {
                stringBuilder.append(S.substring(lastIndex));
            }
            return stringBuilder.toString();
        }
        return S;
    }

    private List<_Matched> findSubString(String s, String child) {
        List<_Matched> matcheds = new ArrayList<>();
        int index = 0;
        while ((index = s.indexOf(child, index)) != -1) {
            matcheds.add(new _Matched(index, index + child.length()));
            /// 这里 index++而不是 index + child.length()
            /// 因为 s: ccc child: cc 得到的结果是 [cc(0, 1), cc(1, 2)]
            index++;
        }
        return matcheds;
    }

    public static void main(String[] args) {
        LeetCode758 leetCode758 = new LeetCode758();
//        boolean res = leetCode758.boldWords(new String[]{
//                "ab", "bc"
//        }, "aabcd").equals("a<b>abc</b>d");
//        res = leetCode758.boldWords(new String[]{
//                "ccb","b","d","cba","dc"
//        }, "eeaadadadc").equals("eeaa<b>d</b>a<b>d</b>a<b>dc</b>");
//        res = leetCode758.boldWords(new String[]{
//                "b","dee","a","ee","c"
//        }, "cebcecceab").equals("<b>c</b>e<b>bc</b>e<b>cc</b>e<b>ab</b>");
//        res = leetCode758.boldWords(new String[]{
//                "cc","eae","eda","e","d"
//        }, "eecaedbded").equals("<b>ee</b>ca<b>ed</b>b<b>ded</b>");
//
//        leetCode758.boldWords(new String[]{
//                "e","ad","ce","a","b"
//        }, "adcaddeced").equals("<b>ad</b>c<b>ad</b>d<b>ece</b>d");
//
//        leetCode758.boldWords(new String[]{
//                "cc"
//        }, "cbccceeead");

        leetCode758.boldWords(new String[]{
                "zgtud", "di","r","buhozb","lofjmyjj","qagllw","zzuid","loyugfh","w","hcfg","ttd","vjqigvx","u","mhbivve","x","nzbvyfzx","zs","j","zm","huevyex","szwigrlwzm","vlrjmobu","b","h","gcmdgyv","anyfelm","vtcejv","myjjzn","jznnj","awcxmjn","lw","sju","szszwigrl","eze","ffikvecua","bklrhsju","gyazwel","pdhnsxsod","zn","rhsjus","zk","gctgu","vzndt","mfd","jlws","j","zxgaudyo","apa","znvixpdh","tgubzczgt"
        }, "wwcyuaqzgtudmpjkluqoseslygywzkixjqghsocvjqigvxwqloyugfhcjscjghqmiglgyazwelshzapaezqgmcmrmfrfzttdgquizyducbvxzzuiddcnwuaapdunzlbagnifndbjyalqqgbramhbivvervxrtcszszwigrlwzmuteyswzagudtpvlrjmobuhozbghkhvoxawcxmjnazlqlkqqqnoclufgkovbokvkoezeknwhcfgcenvaablpvtcejvzndtzncrelhedwlwiqgdbdgctgubzczgtovufncicjlwsmfdcrqeaghuevyexqdhffikvecuazrelofjmyjjznnjdkimbklrhsjusbstqhvlejtjcczqnzbvyfzxgaudyosckysmminoanjmbafhtnbrrzqagllwxlxmjanyfelmwruftlzuuhbsjexoobjkmymlitiwjtdxscotzvznvixpdhnsxsodieatipiaodgcmdgyv");
    }

}
