package at.ac.fhcampuswien.fhmdb.models;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.*;


public class MovieAPI {
    private static final String URL = "http://localhost:8080/movies";
    private static final String DELIMITER = "&";

    private static String buildURL(String query, Genres genre, String releaseYear, String ratingFrom){

        StringBuilder url = new StringBuilder(URL);

        if((query != null && !query.isEmpty())|| genre != null || releaseYear != null || ratingFrom != null) {
            url.append("?");
            if(query != null && !query.isEmpty()){
                url.append("query=").append(query).append(DELIMITER);
            }
            if(genre != null){
                url.append("genre=").append(genre).append(DELIMITER);
            }
            if(releaseYear != null){
                url.append("releaseYear=").append(releaseYear).append(DELIMITER);
            }
            if(ratingFrom != null){
                url.append("ratingFrom=").append(ratingFrom).append(DELIMITER);
            }
        }
        return url.toString();
    }

    public static List<Movie> getAllMovies(){
        return getAllMovies(null, null, null, null);
    }

    public static List<Movie> getAllMovies(String query, Genres genre, String releaseYear, String ratingFrom){
        String url = buildURL(query, genre, releaseYear, ratingFrom);

        Request request = new Request.Builder()
                .url(url)
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "http.agent")
                .build();

        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()){
            String responseBody = response.body().string();
            Gson gson = new Gson();
            System.out.println(responseBody);
            
            Movie[] movies = gson.fromJson(responseBody, Movie[].class);

            return Arrays.asList(movies);
        }
        catch (Exception e){
            System.err.println();
        }
        return new ArrayList<>();
    }
}

