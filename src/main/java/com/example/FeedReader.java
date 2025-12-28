package com.example;
import java.net.URI;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class FeedReader {
    public static String getLatestTweetLink(String feedURL){
        try{
            URL url = URI.create(feedURL).toURL();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url.openStream());

            NodeList items = doc.getElementsByTagName("item");

            if(items.getLength() > 0){
                Element item = (Element) items.item(0);
                return item.getElementsByTagName("link")
                .item(0)
                .getTextContent();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
