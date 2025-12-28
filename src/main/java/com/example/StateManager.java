package com.example;

import java.nio.file.Files;


public class StateManager {
   private static final String FILE="lastSeen.txt";
   
   public static String getLastSeen(){
    try {
        return Files.readString(java.nio.file.Path.of(FILE));
    } catch (Exception e) {
        return "";
    }
   }

   public static void updateLastSeen(String latestTweet){
    try {
        Files.writeString(java.nio.file.Path.of(FILE), latestTweet);
    } catch (Exception e) {
        e.printStackTrace();
    }
   }
}
