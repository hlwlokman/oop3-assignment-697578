package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*;

public class OmdbClient {
    private static final String API_KEY = "dbf55059"; 

    public static Movie fetchMovie(String title) {
        try {
            String formattedTitle = title.replace(" ", "+");
            String urlStr = "http://www.omdbapi.com/?t=" + formattedTitle + "&apikey=" + API_KEY;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            JsonObject obj = JsonParser.parseString(result.toString()).getAsJsonObject();
            Movie movie = new Movie();
            movie.title = obj.get("Title").getAsString();
            movie.year = obj.get("Year").getAsString();
            movie.director = obj.get("Director").getAsString();
            movie.genre = obj.get("Genre").getAsString();
            return movie;

        } catch (Exception e) {
            System.out.println("OMDb error: " + e.getMessage());
            return new Movie();
        }
    }
}
