package assignment4;

import javax.xml.bind.ValidationEvent;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;


/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     *
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweets> writtenBy(List<Tweets> tweets, String username) {
        List<Tweets> filteredList = new ArrayList<Tweets>();
        int j = 0;
        String comparison = "";

        //By going through each tweet, you have to compare the name of each tweet to its username
        //and see whether it equals the username

        for(int i = 0; i < tweets.size(); i++)
        {
            Tweets aTweet = tweets.get(i);
            comparison = aTweet.getName().toLowerCase();
            if(comparison.equals(username))  //if the name equals the username
            {
                filteredList.add(j, aTweet);  //then add the tweet to the filteredList
                j++;
            }

        }

        return filteredList;
    }

    /**
     * Find tweets that were sent during a particular timespan.
     *
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweets> inTimespan(List<Tweets> tweets, Timespan timespan) {
        List<Tweets> filteredList = new ArrayList<Tweets>();
        Instant startTime = timespan.getStart();
        Instant endTime = timespan.getEnd();
        int indexTweet = 0;

        if(startTime.isAfter(endTime))
        {
            throw new IllegalArgumentException("End Date before Start Date");
        }

       //Checking for timeSpan before analyzing tweets
        //exception

        //Iterating through each tweet
        for(Tweets timeTweet : tweets)
        {
            String startTimeforTweet = timeTweet.getDate();   //Getting the start time for a tweet
            Instant currentTweetTime = Instant.parse(startTimeforTweet);    //Obtain the currentTweetTime by parsing the startTimeforTweet
            if(currentTweetTime.isAfter(startTime) && currentTweetTime.isBefore(endTime))
            {
                filteredList.add(indexTweet, timeTweet);
                indexTweet++;   //Place the tweets whose currentTweetTime is after the startTime and before the endTime
            }

        }

        return filteredList;
    }

    /**
     * Find tweets that contain certain words.
     *
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets.
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when
     *         represented as a sequence of nonempty words bounded by space characters
     *         and the ends of the string) includes *at least one* of the words
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweets> containing(List<Tweets> tweets, List<String> words) {
        List<Tweets> filteredList = new ArrayList<Tweets>();
        String combinedString = concatenate(words);


        //By going through each tweet, we check the text for each tweet
        for(Tweets tempTweet : tweets)
        {
            String checkText = tempTweet.getText().toLowerCase();
            for(int j = 0; j < words.size(); j++)
            {
                //Determine whether the text contains at least one of the words from the words list
                if(checkText.contains(words.get(j)))
                {
                    filteredList.add(j, tempTweet);   //If this is the case, then add the tweet to the filteredList in the order of the input List
                    j++;
                }
            }
        }

        return filteredList;
    }

    private static String concatenate(List<String> possibleWords)
    {
        String temp = "";
        for(String k : possibleWords)
        {
            temp = temp + k;
        }

        return temp;
    }
}