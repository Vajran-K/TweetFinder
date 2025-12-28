package com.example;

public class App {
    public static void main(String[] args) throws Exception {
        
        String tweetURL  = "https://rss.app/feeds/rm0TjdzvVYRfbzbD.xml";

        String lastSeen =  StateManager.getLastSeen();
        String latestTweet = FeedReader.getLatestTweetLink(tweetURL);

        if(latestTweet != null && !latestTweet.equals(lastSeen)){

            EmailSender.sendEmail("New Tweet!",
            "Latest tweet: " + latestTweet);

            StateManager.updateLastSeen(latestTweet);
            System.out.println("New tweet detected and email sent.");
        }else{
            System.out.println("No new tweet detected.");
        }
    }
}