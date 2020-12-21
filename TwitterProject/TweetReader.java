package assignment4;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.time.Instant;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import okhttp3.*;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * TweetReader contains method used to return tweets from method
 * Do not change the method header
 */
public class TweetReader {

    private final OkHttpClient httpClient = new OkHttpClient();
    /**
     * Find tweets written by a particular user.
     *
     * @param url
     *            url used to query a GET Request from the server
     * @return return list of tweets from the server
     *
     */
    public static List<Tweets> readTweetsFromWeb(String url) throws Exception
    {
        List<Tweets> tweetList = new ArrayList<>();
        List<Tweets> rightFormatTweets = new ArrayList<>();
        String unfiltered;
        OkHttpExample obj = new OkHttpExample();
        String tweetObjects;
        System.out.println("Testing - Send Http GET Request");
        unfiltered = obj.sendGet(url);
        tweetList= turnStringsToTweets(unfiltered);
        rightFormatTweets = cleanTweets(tweetList);
        return rightFormatTweets;
    }

    /*
    This method turn the json string consisting of tweets into a list of tweets
     */
    private static List<Tweets> turnStringsToTweets(String stringsUnfiltered) throws IOException, JsonMappingException
    {
        ObjectMapper mapper = new ObjectMapper();  //Using the mapper it can turn the string into the list of tweets
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        List<Tweets> tweeters = Arrays.asList(mapper.readValue(stringsUnfiltered, Tweets[].class));

        return tweeters;    //tweeters has all the tweets specified from the json string
    }

    /*
    Returns the valid tweets that are following the specifications of the assignment description
     */
    private static List<Tweets> cleanTweets(List<Tweets> listOfTweets) {
        List<Tweets> filtered = new ArrayList<Tweets>();
        List<Tweets> semiFiltered = new ArrayList<>();  //list of valid tweets
        int j = 0;
        String EMPTY = "";

        semiFiltered = checkNameAndText(listOfTweets);
        //Returns the list of tweets that have a valid name and have a text limit of 140 characters
        //Now we check whether the tweets have a valid date

        for(Tweets DateCheck: semiFiltered)
        {
            //By using a try-catch exception, we are able to handle the DateTimeParseException for improperly formatted dates
            String Date = DateCheck.getDate();
            try
            {
                Instant isDateValid = Instant.parse(Date);
                filtered.add(j, DateCheck);    //adding the dates in order that the input tweets are in
                j++;
            }
            catch(DateTimeParseException e)
            {
                //System.out.println(DateCheck);
            }
        }


        return filtered;

    }

    private static List<Tweets> checkNameAndText(List<Tweets> listIsUnfiltered)
    {
        String name;
        String text;
        List<Tweets> filteredByNameAndText = new ArrayList<>();
        int j = 0;
        int k = 0;

        for(Tweets Check : listIsUnfiltered)  //As you go through each tweet
        {
            name = Check.getName();
            text = Check.getText();
            if(IsNameValid(name))           //If the name of the tweet is valid, then we have to check the limit of the length of the text
            {
                if(text.length() <= 140)    //If the limit of the text is less than or equal to 140 characters, then add it to the filteredByNameandText list
                {
                    filteredByNameAndText.add(j, Check);
                    j++;
                }
            }
        }

        return filteredByNameAndText;

    }

    private static boolean IsNameValid(String nameOfTweet)
    {

        //For the name of the tweet to be valid, it has to contain the valid characters A-Za-z0-9 and _
        //If one of the characters of the string is none of these mentioned here, then this method will return false

        for(int i = 0; i < nameOfTweet.length(); i++)
        {
            if((nameOfTweet.charAt(i) >= 'A') && (nameOfTweet.charAt(i) <= 'Z') || (nameOfTweet.charAt(i) >= 'a') && (nameOfTweet.charAt(i) <= 'z') || ((nameOfTweet.charAt(i) >= '0') && (nameOfTweet.charAt(i) <= '9')) || nameOfTweet.charAt(i) == '_')
            {}
            else
            {
                return false;
            }

        }

        return true;
    }
}
