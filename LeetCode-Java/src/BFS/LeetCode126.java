package BFS;

import java.util.*;
import java.util.function.Consumer;

public class LeetCode126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return res;
        }
        Map<String, List<String>> wordGraph = generatorWordGraph(wordList);
        Queue<List<String>> queue = new LinkedList<>();
        List<String> beginPath = new ArrayList<>();
        beginPath.add(beginWord);
        queue.add(beginPath);

        Set<String> visitedPath = new HashSet<>();
        visitedPath.add(beginWord);

        int pathCount = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int currentLevelCount = queue.size();
            List<String> nextWords = new ArrayList<>();
            for (int count = 0; count < currentLevelCount; count++) {
                final List<String> currentPath = queue.poll();
                if (currentPath.size() > pathCount) {
                    break;
                }
                final String currentWord = currentPath.get(currentPath.size() - 1);
                if (currentWord.equals(endWord)) {
                    pathCount = currentPath.size();
                    List<String> temp = new ArrayList<>(currentPath);
                    res.add(temp);
                    continue;
                }
                final int wordLen = currentWord.length();
                for (int i = 0; i < wordLen; i++) {
                    String wordKey = currentWord.substring(0, i) + "*" + currentWord.substring(i + 1, wordLen);
                    List<String> targetWords = wordGraph.getOrDefault(wordKey, new ArrayList<>());
                    for (String targetWord : targetWords) {
                        if (!visitedPath.contains(targetWord)) {
                            List<String> newPath = new ArrayList<>(currentPath);
                            newPath.add(targetWord);
                            /// 记录所有的下一个节点，等循环完本次 level 时，记录
                            /// 不能直接添加到 visited 中
                            /// dot -> cot -> cog
                            /// dot -> dog -> cog
                            /// 第一条 path 在找到 cog 时，如果加入到 visited 中，那么第二条就无法找到结束点了
                            nextWords.add(targetWord);
                            queue.add(newPath);
                        }
                    }
                }
            }
            visitedPath.addAll(nextWords);
        }

        return res;
    }

    /**
     * 因为每次只能替换一个字符，所以我们将所有的 word，按照共有字符串分类即
     * 1. 一个长为n 的单词，可以有 n 个分类方法，比如 hit：*it，h*t，hi*
     * 2. 将具有相同分类的字符串放到同一个分类标签下，比如 dot，hot => *ot: [dot, hot]
     * @param wordList 单词列表
     * @return 分类完成后的 Graph
     */
    private Map<String, List<String>> generatorWordGraph(List<String> wordList) {
        Map<String, List<String>> wordGraph = new HashMap<>();
        wordList.forEach(word -> {
            final int wordLen = word.length();
            /// [hit, hot, dot, dog]
            /// *it: [hit], h*t: [hit, hot], hi*: [hit]
            /// *ot: [hot, dot], d*t: [dot], do*: [dot, dog]
            /// ho*: [hot], d*g: [dog]
            for (int index = 0; index < wordLen; index++) {
                String wordKey = word.substring(0, index) + "*" + word.substring(index + 1, wordLen);
                List<String> words = wordGraph.getOrDefault(wordKey, new ArrayList<>());
                words.add(word);
                wordGraph.put(wordKey, words);
            }
        });
        return wordGraph;
    }

    public static void main(String[] args) {
        LeetCode126 leetCode126 = new LeetCode126();
//        leetCode126.findLadders("hit", "coo", Arrays.asList("hot","dot","dog","lot","log","cog", "coo"));
        leetCode126.findLadders("cet", "ism", Arrays.asList(
                "kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"
        ));
    }
}
