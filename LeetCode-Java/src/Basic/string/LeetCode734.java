package Basic.string;

import java.util.*;

public class LeetCode734 {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        /// 句子只会在具有相同单词个数的前提下才会相似
        if (words1.length != words2.length) {
            return false;
        }
        if (pairs.size() == 0) {
            for (int i = 0; i < words1.length; i++) {
                String value1 = words1[i];
                String value2 = words2[i];
                if (!value1.equals(value2)) {
                    return false;
                }
            }
            return true;
        }
        /// create pairs map
        Map<String, Set<String>> dictionary = new HashMap<>();
        for (List<String> pair : pairs) {
            String value1 = pair.get(0);
            String value2 = pair.get(1);
            Set<String> valu1s = dictionary.get(value1);
            if (valu1s == null) {
                valu1s = new HashSet<>();
            }
            valu1s.add(value2);
            dictionary.put(value1, valu1s);

            Set<String> valu2s = dictionary.get(value2);
            if (valu2s == null) {
                valu2s = new HashSet<>();
            }
            valu2s.add(value1);
            dictionary.put(value2, valu2s);
        }

        for (int i = 0; i < words1.length; i++) {
            String realString = words2[i];
            if (words1[i].equals(realString)) {
                continue;
            }
            Set<String> wishStrings = dictionary.get(words1[i]);
            if (wishStrings == null || !wishStrings.contains(realString)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode734 leetCode734 = new LeetCode734();

        String[] words1 = new String[] {"one","excellent","meal"};
        String[] words2 = new String[] {"one","good","dinner"};
        List<List<String>> pairs = new ArrayList<>();
        String[][] strings = new String[][]{{"great","good"},{"extraordinary","good"},{"well","good"},{"wonderful","good"},{"excellent","good"},{"fine","good"},{"nice","good"},{"any","one"},{"some","one"},{"unique","one"},{"the","one"},{"an","one"},{"single","one"},{"a","one"},{"truck","car"},{"wagon","car"},{"automobile","car"},{"auto","car"},{"vehicle","car"},{"entertain","have"},{"drink","have"},{"eat","have"},{"take","have"},{"fruits","meal"},{"brunch","meal"},{"breakfast","meal"},{"food","meal"},{"dinner","meal"},{"super","meal"},{"lunch","meal"},{"possess","own"},{"keep","own"},{"have","own"},{"extremely","very"},{"actually","very"},{"really","very"},{"super","very"}};
        Arrays.asList(strings);
        leetCode734.areSentencesSimilar(words1, words2, pairs);
    }
}
