class Twitter {
    private static int timestamp = 0;
    
    private static class Tweet {
        int id;
        int time;
        Tweet next;
        
        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
            this.next = null;
        }
    }
    
    // Maps userId -> set of followeeIds
    private Map<Integer, Set<Integer>> followMap;
    // Maps userId -> head of tweet linked list (most recent first)
    private Map<Integer, Tweet> tweetMap;

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId, timestamp++);
        // Prepend new tweet to the user's tweet list
        newTweet.next = tweetMap.get(userId);
        tweetMap.put(userId, newTweet);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new ArrayList<>();
        
        // PriorityQueue orders tweets by most recent (highest timestamp first)
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> b.time - a.time);
        
        // Include the user's own tweets
        Tweet userHead = tweetMap.get(userId);
        if (userHead != null) {
            maxHeap.offer(userHead);
        }
        
        // Include the latest tweet from each followee
        Set<Integer> followees = followMap.get(userId);
        if (followees != null) {
            for (int followeeId : followees) {
                // Avoid re-adding user's own head if they accidentally followed themselves
                if (followeeId == userId) continue;
                Tweet followeeHead = tweetMap.get(followeeId);
                if (followeeHead != null) {
                    maxHeap.offer(followeeHead);
                }
            }
        }
        
        // Extract up to 10 most recent tweets
        while (!maxHeap.isEmpty() && feed.size() < 10) {
            Tweet curr = maxHeap.poll();
            feed.add(curr.id);
            if (curr.next != null) {
                maxHeap.offer(curr.next);
            }
        }
        
        return feed;
    }
    
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> followees = followMap.get(followerId);
        if (followees != null) {
            followees.remove(followeeId);
        }
    }
}