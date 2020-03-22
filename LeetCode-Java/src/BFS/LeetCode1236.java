package BFS;

import java.util.*;

interface HtmlParser {
    List<String> getUrls(String url);
}

public class LeetCode1236 {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        List<String> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(startUrl);
        visited.add(startUrl);

        while (!queue.isEmpty()) {
            String url = queue.poll();
            res.add(url);
            List<String> subURLs = htmlParser.getUrls(url);
            for (String subURL : subURLs) {
                if (!hasDiffHost(url, subURL) && !visited.contains(subURL)) {
                    queue.offer(subURL);
                    visited.add(subURL);
                }
            }
        }
        return res;
    }

    boolean hasDiffHost(String url, String subURL) {
        return !getHost(url).equals(getHost(subURL));
    }

    private String  getHost(String url) //解析获取url的域名
    {
        int cnt=3,
                i = 0;
        for (; i <url.length()&&cnt>0 ; i++) {
            if (url.charAt(i)=='/')cnt--;
        }

        if (i==url.length())return url;
        return  url.substring(0,i-1);
    }

    public static void main(String[] args) {
    }
}
