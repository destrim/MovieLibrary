package com.destrim.service;

import com.destrim.exception.BadApikeyException;
import com.destrim.exception.MovieInOmdbNotFound;
import com.destrim.exception.OmdbConnectionProblem;
import com.destrim.model.Movie;
import com.destrim.model.MovieDTO;
import com.destrim.util.FileHandling;
import com.destrim.util.ParseJSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class OmdbWebServiceClient {

    private static final String SEARCH_URL = "http://www.omdbapi.com/?apikey=APIKEY&type=movie&t=TITLE&y=YEAR";
    private final String apikey;

    public OmdbWebServiceClient() throws BadApikeyException {
        this.apikey = FileHandling.importApikey().orElseThrow(BadApikeyException::new);
    }

    // TODO Optional instead of MovieInOmdbNotFound Exception
    public MovieDTO searchMovieByTitleYear(String title, String year) throws MovieInOmdbNotFound, OmdbConnectionProblem {
        title = URLEncoder.encode(title, StandardCharsets.UTF_8);

        String requestUrl = SEARCH_URL
                .replaceAll("TITLE", title)
                .replaceAll("YEAR", year)
                .replaceAll("APIKEY", apikey);

        String response = sendGetRequest(requestUrl);
        if (!ParseJSON.isResponseCorrect(response)) {
            throw new MovieInOmdbNotFound();
        }
        return ParseJSON.parse(response);
    }

    private String sendGetRequest(String requestUrl) throws OmdbConnectionProblem {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = prepareHttpURLConnection(url);

            InputStream stream = connection.getInputStream();
            StringBuilder response = parseResponse(stream);

            connection.disconnect();
            return response.toString();
        } catch (IOException e) {
            throw new OmdbConnectionProblem();
        }
    }

    private HttpURLConnection prepareHttpURLConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        return connection;
    }

    private StringBuilder parseResponse(InputStream stream) throws IOException {
        StringBuilder response = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffer = new BufferedReader(reader);

        String line;
        while ((line = buffer.readLine()) != null) {
            response.append(line);
        }

        buffer.close();
        return response;
    }
}
