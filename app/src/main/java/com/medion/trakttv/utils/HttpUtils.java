package com.medion.trakttv.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.medion.trakttv.data.MovieInfo;
import com.medion.trakttv.jsondata.MovieData;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhan on 7/13/16.
 */
public class HttpUtils {
    private static final String TAG = "HttpUtils";

    private static class HttpUtilsHolder{
        private static final HttpUtils instance = new HttpUtils();
    }

    public static final HttpUtils getInstance(){
        return HttpUtilsHolder.instance;
    }

    private  HttpUtils(){
    }

    public int getfromTraktTv(int pageIndex, int pageCount, String queryFilter, ArrayList<MovieInfo> movieInfoList) {
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        int statusCode = 0;
        try {
            /* forming th java.net.URL object */
            String urlString = String.format("https://api.trakt.tv/movies/popular/?page=%d&limit=%d&extended=full,images&query=%s", pageIndex, pageCount, queryFilter);
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            /* optional request header */
            urlConnection.setRequestProperty("Content-Type", "application/json");

            /* optional request header */
            urlConnection.setRequestProperty("trakt-api-key", "ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086");

            /* optional request header */
            urlConnection.setRequestProperty("trakt-api-version", "2");

            /* for Get request */
            urlConnection.setRequestMethod("GET");
            statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
            if (statusCode == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                String response = convertInputStreamToString(inputStream);
                List<MovieData> movieResults = parseResult(response);

                // Convert json data to UI data
                convertMovieDataToMovieInfo(movieResults, movieInfoList);

            } else {
            }
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
        return statusCode; //"Failed to fetch data!";
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

            /* Close Stream */
        if (null != inputStream) {
            inputStream.close();
        }
        return result;
    }

    private List<MovieData> parseResult(String result) {
        List<MovieData> movieResults = new ArrayList<>();
        Type collectionType = new TypeToken<List<MovieData>>(){}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(collectionType, new MultimediaDeserializer());
        Gson gson = gsonBuilder.create();
        movieResults = gson.fromJson(result, collectionType);
        return movieResults;
    }

    private void convertMovieDataToMovieInfo(List<MovieData> movieResults, ArrayList<MovieInfo> movieInfoList)
    {
        if(movieResults != null && movieInfoList != null)
        {
            for(MovieData data : movieResults){
                MovieInfo movieInfo = new MovieInfo();

                movieInfo.setTitle(data.getTitle());
                movieInfo.setThumbnail(data.getImages().getPoster().getThumb());
                movieInfo.setReleaseyear(data.getReleased());
                movieInfo.setOverview(data.getOverview());

                movieInfoList.add(movieInfo);
            }
        }
    }
}
