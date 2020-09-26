package DFS;

public class Interview1618 {
    public boolean patternMatching(String pattern, String value) {
        /// 1. 统计 pattern 中 a，b 各自的长度 count_a, count_b
        /// count_a * value_a + (count_b * value_b) = value_l

        int count_a = 0, count_b = 0;
        for (char ch : pattern.toCharArray()) {
            if (ch == 'a') {
                count_a++;
            } else {
                count_b++;
            }
        }
        /// 2. 为了保证 count_a > 0, 如果 count_a < count_b 的时候需要将 a，b 互换
        if (count_a < count_b) {
            int temp = count_a;
            count_a = count_b;
            count_b = temp;
            char[] array = pattern.toCharArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] == 'a' ? 'b' : 'a';
            }
            pattern = new String(array);
        }

        for (int len_a = 0; len_a * count_a <= value.length(); len_a++) {
            /// value 中需要匹配 b 字符的字符个数
            int rest = value.length() - len_a * count_a;
            if (rest == 0 && count_b == 0 || (count_b != 0 && rest % count_b == 0)) {
                /// b 对应字符串的长度
                int len_b = count_b == 0 ? 0 : rest / count_b;
                int pos = 0;
                boolean correct = true;
                String value_a = "", value_b = "";
                for (char ch: pattern.toCharArray()) {
                    if (ch == 'a') {
                        String aPattern = value.substring(pos, pos + len_a);
                        if (value_a.length() == 0) {
                            value_a = aPattern;
                        } else if (!value_a.equals(aPattern)) {
                            correct = false;
                            break;
                        }
                        pos += len_a;
                    } else {
                        String bPattern = value.substring(pos, pos + len_b);
                        if (value_b.length() == 0) {
                            value_b = bPattern;
                        } else if (!value_b.equals(bPattern)) {
                            correct = false;
                            break;
                        }
                        pos += len_b;
                    }
                }
                if (correct && !value_a.equals(value_b)) {
                    return true;
                }
            }

        }
        return false;
    }
}
