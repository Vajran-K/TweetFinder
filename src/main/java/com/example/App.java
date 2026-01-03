package com.example;

public class App {

    public static boolean runScan() {
    System.out.println("=== runScan STARTED ===");

    try {
        String tweetURL = ConfigLoader.get("rss.url");
        System.out.println("RSS URL = " + tweetURL);

        String lastSeen = StateManager.getLastSeen();
        System.out.println("Last seen = " + lastSeen);

        String latestTweet = FeedReader.getLatestTweetLink(tweetURL);
        System.out.println("Latest tweet = " + latestTweet);

        if (latestTweet != null && !latestTweet.equals(lastSeen)) {
            System.out.println("NEW TWEET FOUND, SENDING EMAIL");

            EmailSender.sendEmail(
                "New Tweet!",
                "Latest tweet: " + latestTweet
            );

            StateManager.updateLastSeen(latestTweet);
            System.out.println("Email sent & state updated");
            return true;
        }

        System.out.println("No new tweet");
        return false;

    } catch (Exception e) {
        System.out.println("ERROR INSIDE runScan");
        e.printStackTrace();
        return false;
    }
}

    public static void main(String[] args){
        runScan();
    }
}