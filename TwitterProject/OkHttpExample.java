package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;

public class OkHttpExample
{
    private final OkHttpClient httpClient = new OkHttpClient(); //SETTING UP THE HTTP CLIENT
    //private ObjectMapper mapper = new ObjectMapper();
    private String jsonToString;


    public String sendGet(String url) throws Exception
    {
        List<String> unfilteredTweets = new ArrayList<String>();
        ObjectMapper mapper = new ObjectMapper();       //Mapping the JSON String to a list of tweets
        Request request = new Request.Builder()       //Initiating a Request to the server
                .url(url)
                .build();


        try(Response response = httpClient.newCall(request).execute())   //As you obtain the response from the client
        {
            if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            assert response.body() != null;
            jsonToString = response.body().string();
            jsonToString.toLowerCase();    //Obtain the jsonString
        }

        return jsonToString;

    }
}
