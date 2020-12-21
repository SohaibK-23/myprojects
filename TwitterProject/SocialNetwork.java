package assignment4;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Collections;
import java.util.Comparator;

import static java.util.stream.Collectors.toMap;

/**
 * Social Network consists of methods that filter users matching a
 * condition.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Get K most followed Users.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @param k
     *            integer of most popular followers to return
     * @return the set of usernames who are most mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getName()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like ethomaz@utexas.edu does NOT
     *         contain a mention of the username.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static List<String> findKMostFollower(List<Tweets> tweets, int k)
    {

        List<String> mostFollowers = new ArrayList<>();  //These are going to be the top k most followed users on twitter
        Map<String, Set<String>> tweetFollowMap = new HashMap<>();
        //This is a map where I have stored all the people as a set who have mentioned each username in the tweets list
        Map<String, ArrayList<String>> originalMap = createTweetMap(tweets);
        //First of all, I created a map where each username of the tweet stores his mentions that he makes in an arrayList

        Map<String, Integer> followerMap = new HashMap<>();
        //This map will specify how many users have mentioned each username specified in this map.
        //For instance, by accessing the key Bob, we are able to see how many times he has been mentioned in various tweets

        for(Tweets tweet : tweets)
        {
            //For each tweet made, we have to check which users have mentioned tweetUserName by examining the list of tweets
            //We are assuming that the tweetUserName has not mentioned himself.
            String tweetUserName = tweet.getName().toLowerCase();
            if(!tweetFollowMap.containsKey(tweetUserName)) {
                Set<String> setOfFriends = new HashSet<>();
                setOfFriends.clear();
                tweetFollowMap.put(tweetUserName, setOfFriends);
            }

            for(int tweetIndex = 0; tweetIndex < tweets.size(); tweetIndex++)
            {
                String tweetText = tweets.get(tweetIndex).getText();  //For each tweet, get the text made by the tweet and check
                                                                      //to see if it has any valid mentions
                List<String> validMentions = checkforValidMentions(tweetText);   //This function will return a list of valid mentions made by the text of the tweet
                if(validMentions.size() > 0)
                {
                    for(int indexOfValidMentions = 0; indexOfValidMentions < validMentions.size(); indexOfValidMentions++)
                    {
                        //Going through each valid mention, we check if tweetUserName is one of their mentions by obtaining its value from the map
                        String currentValidMention = validMentions.get(indexOfValidMentions);
                        if(originalMap.get(currentValidMention).contains(tweetUserName))
                        {
                            //If the currentValidMention does contain tweetUserName as one of their mentions, then add it to the set of people who have mentioned tweetUserName
                            tweetFollowMap.get(tweetUserName).add(currentValidMention);
                        }

                    }
                }
            }      //I continued this process as I examined the text of each tweet
        }     //I also added the usernames for each tweet to the tweetFollowMap


        //This loop is going to place the user as the key and the size of the set from the tweetFollowMap as a value into
        //the map followerMap.
        //This will help us sort the followerMap for how many followers each user has
        for(String user: tweetFollowMap.keySet())
        {
            Set<String> Friends = tweetFollowMap.get(user);
            followerMap.put(user, Friends.size());
        }

        /*
        List<Map.Entry<String, Integer>> followerList = new LinkedList<Map.Entry<String, Integer>> (followerMap.entrySet());
        Collections.sort(followerList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });



        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> entry: followerList)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        */


        Map<String, Integer> sortedMap = followerMap;   //This is the sorted Map where the values are arranged from descending to ascending order
        sortedMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        //sortedMap is the map whose values are descending

        /*
        This loop helps us to determine the top k followers on twitter by taking each name from the sorted map and placing it
        in the mostFollowers list
         */
        for(String name: sortedMap.keySet())
        {
            for(int followerIndex = 0; followerIndex <= k; followerIndex++)
            {
                mostFollowers.add(name);
            }
        }


        return mostFollowers;
    }

    /*
    The purpose of this map is to see which username made mentions to what kind of people
     */
    private static Map<String, ArrayList<String>> createTweetMap(List<Tweets> tweets)
    {
        Map<String, ArrayList<String>>  tweetMap =  new HashMap<String, ArrayList<String>>();  //tweetMap used to refer to each username
        String tweetText;
        //Creating the map
        //For every tweet in the list of tweets, get the text of the tweet to see if there any valid mentions made on the tweet
        //If there are any valid mentions, the size of the validMentions list will be greater than 0.
        for(int tweetIndex = 0; tweetIndex < tweets.size(); tweetIndex++)
        {
            tweetText = tweets.get(tweetIndex).getText();
            List<String> validMentions = checkforValidMentions(tweetText);
            //These are the mentions that the text of the tweet contains.
            //If there are any valid mentions made in the text
            if(validMentions.size() > 0)
            {
                String tweetUsername = tweets.get(tweetIndex).getName().toLowerCase();   //Obtain the username for the tweet
                if(tweetMap.containsKey(tweetUsername))   //If the username already exists within the map, then we only need to add the mention of the username if it doesn't exist
                {
                    for(int indexOfValidMentions = 0; indexOfValidMentions < validMentions.size(); indexOfValidMentions++)
                    {
                        String currentValidMention = validMentions.get(indexOfValidMentions).toLowerCase();
                        if(!tweetMap.get(tweetUsername).contains(currentValidMention))
                        {
                            tweetMap.get(tweetUsername).add(currentValidMention);   //add the mention if they didn't exist in the tweetUsername's mentions
                        }
                    }
                }
                else
                {
                    tweetMap.put(tweetUsername, (ArrayList<String>) validMentions);   //If the username doesn't exist within the map, then add it to the map along with his mentions
                }
            }
        }

        return tweetMap;  //return the tweetMap
    }

    /**
     * Find all cliques in the social network.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     *
     * @return list of set of all cliques in the graph
     */
    List<Set<String>> findCliques(List<Tweets> tweets) {

        /*
        To find all the cliques for the list of tweets, I created a tweetMap where every username of the tweet
        maps to an arrayList of the mentions that they made on the texts for each tweet.
         */

        List<Set<String>> result = new ArrayList<Set<String>>();
        String tweetText;
        Map<String, ArrayList<String>>  tweetMap =  new HashMap<String, ArrayList<String>>();  //tweetMap used to refer to each usernamions

        //Creating the map
        //For every tweet in the list of tweets, get the text of the tweet to see if there any valid mentions made on the tweet
        //If there are any valid mentions, the size of the validMentions list will be greater than 0.


        //Please look at the comments for the method createTweetMap for seeing how I made the tweetMap
        for(int tweetIndex = 0; tweetIndex < tweets.size(); tweetIndex++)
        {
            tweetText = tweets.get(tweetIndex).getText();
            List<String> validMentions = checkforValidMentions(tweetText);
            if(validMentions.size() > 0)
            {
                String tweetUsername = tweets.get(tweetIndex).getName().toLowerCase();
                if(tweetMap.containsKey(tweetUsername))
                {
                    for(int indexOfValidMentions = 0; indexOfValidMentions < validMentions.size(); indexOfValidMentions++)
                    {
                        String currentValidMention = validMentions.get(indexOfValidMentions).toLowerCase();
                        if(!tweetMap.get(tweetUsername).contains(currentValidMention))
                        {
                            tweetMap.get(tweetUsername).add(currentValidMention);
                        }
                    }
                }
                else
                {
                    tweetMap.put(tweetUsername, (ArrayList<String>) validMentions);
                }
            }
        }


        //Please look at the comments for the method createTweetMap to see how I created tweetMap


        result = cliques(tweetMap);
        return result;
    }

    private static List<Set<String>> cliques(Map<String, ArrayList<String>> tempMap)
    {
        List<Set<String>> maximumCliques = new ArrayList<>();
        Set<Set<String>> setFriends = new HashSet<>();    //This is a set of sets where each set added represents the cliques formed

        /*
        The first step is to look at each key of tempMap and obtaining its mentions
         */
        for(String username : tempMap.keySet())
        {
            ArrayList<String> mentions = tempMap.get(username);

            //Iterate through each mention in the mentions list and see if the set setFriends is empty or not
            for(int indexMention = 0; indexMention < mentions.size(); indexMention++)
            {
                String mentionOfUsername = mentions.get(indexMention);
                if(!setFriends.isEmpty())    //If setFriends wasn't empty, then we would go iterate through the set contained in that set
                {                            //and see if there was a mutual friendship within each member of the set and each mention
                    for(Set<String> interiorSet : setFriends)
                    {
                        if(isMutualFriend(interiorSet, mentionOfUsername, tempMap))
                        {
                            //If this was the case, then the inner set would have another name mentionOfUsername added.
                            interiorSet.add(mentionOfUsername);
                        }

                        else
                        {
                            //Otherwise, then it is known that the mentionOfUserName is only being mentioned by one of the people in the inner Set
                            //So, the method followedByOnePerson will return the set consisting of the members mentionOfUserName and one of the people in the set
                            //There might be a possibility of an empty set since the mentionOfUsername might not be mentioned by any one in the set
                           Set<String> separateClique =  followedByOnePerson(interiorSet, mentionOfUsername, tempMap);
                           setFriends.add(separateClique);   //adding the set to the setFriends set
                        }

                    }
                }
                else
                {
                    //If the set SetFriends is empty, then we have to form a clique with the members mentionOfUsername and username
                    //This will be the first Clique set of the setFriends
                    if(tempMap.get(mentionOfUsername).contains(username))
                    {
                        Set<String> tempSet = new HashSet<>();
                        tempSet.add(username);
                        tempSet.add(mentionOfUsername);
                        setFriends.add(tempSet);   //Adding it to the setFriends
                    }
                }


            }

        }

        maximumCliques = turnSetstoLists(setFriends);   //We then turn the set setFriends to a list of sets showing all the cliques that were made

        return maximumCliques;

    }

    private static List<Set<String>> turnSetstoLists(Set<Set<String>> group)
    {
        List<Set<String>> allCliques = new ArrayList<>();

        //Taking each set from the set of sets group and adding it to the allCliques list
        for(Set<String> cliqueSet: group)
        {
            allCliques.add(cliqueSet);
        }

        return allCliques;

    }

    /*
    This function is going to check whether the mentionOfUsername is mentioning and being mentioned by every one of the members in the innerSet
     */
    private static boolean isMutualFriend(Set<String> innerSet, String mentionOfUsername, Map<String, ArrayList<String>> mapOfFriends)
    {

        boolean statusOfMutualFriendship = false;   //Set initially to false. It will be set to true if the mentionOfUsername is mentioning and being mentioned by every one of the members in the innerSet

        //Going through the innerSet
        for(String friend: innerSet)
        {
            ArrayList<String> mentionsOfFriend = mapOfFriends.get(friend);   //Obtaining the arrayList of mentions of the usernames friend and mentionOfUsername
            ArrayList<String> mentionsOfUsername = mapOfFriends.get(mentionOfUsername);
            if(mentionsOfFriend.contains(mentionOfUsername) && mentionsOfUsername.contains(friend))
            //If the above condition where one of the friend's mentions is the mentionOfUsername and one of the username mentions is the friend, then the boolean variable is true
            {
                statusOfMutualFriendship = true;
            }

            else
            {
                statusOfMutualFriendship = false;   //Otherwise, it is considered to be false
            }
        }

        return statusOfMutualFriendship;
    }

    private static Set<String> followedByOnePerson(Set<String> innerSet, String mentionOfUsername, Map<String, ArrayList<String>> friendMap)
    {
        Set<String> set = new HashSet<>();   //This is the set created when the mentionOfUsername is being mentioned and is mentioning only one of the people in the set specified by innerSet
        ArrayList<String> mentionsOfUsername = friendMap.get(mentionOfUsername);  //Specifying the mentionOfUsername with an arrayList

        //By going through the inner set, we check to see whether the friendName is being mentioned by any one of the mentionsOfUsername
        //and the mentionOfUsername is mentioning the friendName
        for(String friendName: innerSet)
        {
            ArrayList<String> mentionsOfFriend = friendMap.get(friendName);  //mentions made by the friendName
            if(mentionsOfFriend.contains(mentionOfUsername) && mentionsOfUsername.contains(friendName))
            {
                set.add(friendName);    //Adding the friendName and its mention to the set. This is a separate set consisting of a separate clique
                set.add(mentionOfUsername);
            }
        }

        return set;
    }


    /*
    This method will return a list of valid mentions given a text of a tweet
     */
    private static List<String> checkforValidMentions(String temp)
    {
        List<String> findAllMatches = new ArrayList<>();
        Matcher m = Pattern.compile("(\\A|[\\s\\^\\&])@[a-zA-Z0-9_]+").matcher(temp);
        //Using regex, I am able to find the pattern associated for each valid mention
        //Helps to separate the valid mentions from the invalid ones
        while(m.find())
        {
            String validMention = m.group();
            if(validMention.charAt(0) == ' ' || validMention.charAt(0) == '^' || validMention.charAt(0) == '&')
                validMention = validMention.substring(2);    //Remove these invalid characters

           else if(validMention.charAt(0) == '@')
                validMention = validMention.substring(1);    //Remove the @ sign if it is the first character mentioned in the text

            findAllMatches.add(validMention);
        }

        return findAllMatches;    //Return the list of all valid mentions
    }
}


