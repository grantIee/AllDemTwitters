import twitter4j.*;
import twitter4j.TwitterStream;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public final class TweetRealTime {

    public static void main(String[] args) throws TwitterException {

        //Get Authentication info from TwitterKey.txt
        File file = new File("TwitterKey.txt");
        String ConsumerKey = null;
        String ConsumerSecret = null;
        String AuthAccessToken = null;
        String AuthAccessSecret = null;

        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(" ");
            ConsumerKey = scanner.next();
            ConsumerSecret = scanner.next();
            AuthAccessToken = scanner.next();
            AuthAccessSecret = scanner.next();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Authenticate and Configure Twitter4j
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(ConsumerKey)
                .setOAuthConsumerSecret(ConsumerSecret)
                .setOAuthAccessToken(AuthAccessToken)
                .setOAuthAccessTokenSecret(AuthAccessSecret)
                .setTweetModeExtended(true);
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
                .getInstance();
        StatusListener listener = new StatusListener() {


            /**
             * This is the code that displays the actual info
             * @param status is the status released by the TwitterStream object
             */
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + "(Followers#: " + status.getUser().getFollowersCount() +
                    ", Friends#: " + status.getUser().getFriendsCount() + ", Favorites#: " + status.getUser().getFavouritesCount() +  ", Account Created: " + status.getUser().getCreatedAt() + ")" +
                    "\n" + "\n" + "\n" + status.getText() + "\n--------------------------------------------------------\n" +
                    status.getUser().getDescription() +
                    "\n==================================================================================");

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };


        twitterStream.addListener(listener);

        //filter specific topic desired
        twitterStream.filter("Bitcoin");
    }
}