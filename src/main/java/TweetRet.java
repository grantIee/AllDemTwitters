import twitter4j.*;

import java.util.List;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.1.7
 */
public class TweetRet {

    public static void main(String[] args) {
        List<Status> apple = getTweets("bitcoin");

        for(Status a : apple) {
            System.out.println(a.getText());
            System.out.println("\n");
            System.out.println(a.getCreatedAt());
            System.out.println("\n");
            System.out.println("\n");
        }
    }
    public static List<Status> getTweets(String topic) {
        List<Status> results = null;
        if(topic.length() < 1) {
            System.out.println("Please enter a topic");
            System.exit(-1);
        }
        Twitter twitter = new TwitterFactory().getInstance();
    try {
        Query query = new Query((topic));
        QueryResult result;
            do {
                result = twitter.search(query);
                results = result.getTweets();
//                for (Status tweet : tweets) {
//                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
//                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        return results;
    }
}