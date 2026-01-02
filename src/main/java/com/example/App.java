package com.example;

public class App {

    public static boolean runScan(){
        String tweetURL  = ConfigLoader.get("rss.url");

        String lastSeen =  StateManager.getLastSeen();
        String latestTweet = FeedReader.getLatestTweetLink(tweetURL);

        if(latestTweet != null && !latestTweet.equals(lastSeen)){

            EmailSender.sendEmail("New Tweet!",
            "Latest tweet: " + latestTweet);

            StateManager.updateLastSeen(latestTweet);
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        runScan();
    }
}