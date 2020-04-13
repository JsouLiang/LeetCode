package Basic;

import java.util.*;


public class LeetCode355 {

    class Tweet {
        int time;
        int tweet;
        Tweet next;
        public Tweet(int time, int tweet) {
            this.time = time;
            this.tweet = tweet;
        }
    }
    class User {
        int userId;
        /// User 关注的人
        List<User> sourceUser;
        Set<Integer> sourceUserIds;

        Tweet tweets;
        public User(int userId) {
            this.userId = userId;
            sourceUser = new LinkedList<>();
            sourceUserIds = new HashSet<>();
            tweets = new Tweet(-1, -1);
        }

        void listen(User user) {
            if (user.userId == userId || sourceUserIds.contains(user.userId)) {
                return;
            }
            sourceUserIds.add(user.userId);
            sourceUser.add(user);
        }

        void unfollow(User user) {
            if (!sourceUserIds.contains(user.userId)) {
                return;
            }
            sourceUser.remove(user);
            sourceUserIds.remove(user.userId);
        }

        void postTweet(Tweet tweet) {
            Tweet head =  tweets.next;
            tweets.next = tweet;
            tweet.next = head;
        }

        boolean hadPostTweet() {
            return tweets.next != null;
        }
    }

    Map<Integer, User> wold;
    int tweetCount;
    PriorityQueue<Tweet> queue;

    /** Initialize your data structure here. */
    public LeetCode355() {
        wold = new HashMap<>();
        tweetCount = 0;
        queue = new PriorityQueue<>((t1, t2) -> t2.time - t1.time);
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
       User user = getUser(userId);
       user.postTweet(new Tweet(tweetCount++, tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        queue.clear();
        User user = getUser(userId);
        if (user.hadPostTweet()) {
            queue.offer(user.tweets.next);
        }
        for (User follower : user.sourceUser) {
            if (follower.hadPostTweet()) {
                queue.offer(follower.tweets.next);
            }
        }
        List<Integer> res = new ArrayList<>(10);
        int count = 0;
        while (!queue.isEmpty() && count < 10) {
            Tweet tweet = queue.poll();
            res.add(tweet.tweet);
            if (tweet.next != null) {
                queue.offer(tweet.next);
            }
            count++;
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        User followee = getUser(followeeId);
        User follower = getUser(followerId);
        follower.listen(followee);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User followee = getUser(followeeId);
        User follower = getUser(followerId);
        follower.unfollow(followee);
    }

    private User getUser(int userId) {
        User user = wold.get(userId);
        if (user == null) {
            User user1 = new User(userId);
            wold.put(userId, user1);
            user = user1;
        }
        return user;
    }

    public static void main(String[] args) {
        LeetCode355 leetCode355 = new LeetCode355();
        leetCode355.postTweet(1, 5);
        /// 1 关注 2
        leetCode355.follow(1, 1);
        leetCode355.postTweet(2, 6);
        leetCode355.getNewsFeed(1);
        leetCode355.unfollow(1, 2);
        leetCode355.getNewsFeed(1);

    }
}
