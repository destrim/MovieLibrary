package com.destrim.service;

import com.destrim.utils.FileHandling;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class OmdbWebServiceClient {

    public static final String SEARCH_URL = "http://www.omdbapi.com/?apikey=APIKEY&type=movie&t=TITLE&y=YEAR";

    public static String sendGetRequest(String requestUrl) {
        StringBuffer response = new StringBuffer();

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connetion = (HttpURLConnection) url.openConnection();
            connetion.setRequestMethod("GET");
            connetion.setRequestProperty("Accept", "*/*");
            connetion.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            InputStream stream = connetion.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader buffer = new BufferedReader(reader);

            String line;
            while ((line = buffer.readLine()) != null) {
                response.append(line);
            }

            buffer.close();
            connetion.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    public static String searchMovieByTitleYear(String title, String year) {
        try {
            title = URLEncoder.encode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String apikey = FileHandling.importApikey();

        String requestUrl = SEARCH_URL
                .replaceAll("TITLE", title)
                .replaceAll("YEAR", year)
                .replaceAll("APIKEY", apikey);

        return sendGetRequest(requestUrl);
    }
}
