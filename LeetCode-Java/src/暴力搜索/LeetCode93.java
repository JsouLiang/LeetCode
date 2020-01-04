package 暴力搜索;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * Example:
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 */
public class LeetCode93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        dfs(s, 0, 0, res);
        return res;
    }

    private void dfs(String s, int currentIndex, int lastPos, List<String> res) {
        if (currentIndex == 3) {
            if (isAviableIPAddress(s)) {
                res.add(s);
            }
            return;
        }
        for (int i = 1; i <=3; i++) {
            StringBuilder stringBuilder = new StringBuilder(s);
            int offset = lastPos + i + currentIndex;
            if (offset < s.length()) {
                stringBuilder.insert(offset, '.');
                dfs(stringBuilder.toString(), currentIndex + 1, lastPos + i, res);
            }
        }
    }

    // (1~255).(0~255).(0~255).(0~255)
    private boolean isAviableIPAddress(String address) {
        if (address == null) {
            return false;
        }
        // IP 地址的长度在 7 ~ 15 之间
        if (address.length() < 7 || address.length() > 15) {
            return false;
        }
        // IP 不能以. 开头和结尾
        if (address.charAt(0) == '.' || address.charAt(address.length() - 1) == '.') {
            return false;
        }

        String[] areas = address.split("\\.");
        if (areas.length != 4) {
            return false;
        }

        for (int index = 0; index < areas.length; index++) {
            String area = areas[index];
            // 01.001.002 非法 IP 地址
            if (area.length() > 1 && area.charAt(0) == '0') {
                return false;
            }
            // IP 中每个字符应该在 0~9 之间
            for (Character areaChar : area.toCharArray()) {
                if (!(areaChar >= '0' && areaChar <= '9')) {
                    return false;
                }
            }

            int areaIntValue = Integer.parseInt(area);
//            if (index == 0) {
//                if (areaIntValue < 1 || areaIntValue > 255) {
//                    return false;
//                }
//            }

            if (areaIntValue < 0 || areaIntValue > 255 ) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LeetCode93 leetCode93 = new LeetCode93();
//        leetCode93.restoreIpAddresses("0");
//        leetCode93.restoreIpAddresses("00");
        leetCode93.restoreIpAddresses("0000");
        leetCode93.restoreIpAddresses("25525511135");
    }
}
