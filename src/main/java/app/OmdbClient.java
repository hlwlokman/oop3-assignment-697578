package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*;

public class OmdbClient {
    // ✅ This is your personal OMDb API key
    private static final String API_KEY = "dbf55059";

    // ✅ This method calls OMDb API and returns a Movie object
    public static Movie fetchMovie(String title) {
        try {
            // 🔧 Format the title for URL (remove spaces etc.)
            String formattedTitle = title.trim().replace(" ", "+");

            // 🌐 Build the OMDb API URL
            String urlStr = "http://www.omdbapi.com/?t=" + formattedTitle + "&apikey=" + API_KEY;
            URL url = new URL(urlStr);

            // 📡 Send HTTP GET request
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 📥 Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            // 🔍 Parse the response as JSON
            JsonObject obj = JsonParser.parseString(result.toString()).getAsJsonObject();

            // ❌ If movie is not found, stop here
            if (!obj.has("Response") || obj.get("Response").getAsString().equals("False")) {
                System.out.println("OMDb error: Movie not found or API issue.");
                return null;
            }

            // ✅ Create and return the Movie object with data from OMDb
            Movie movie = new Movie();
            movie.title = obj.get("Title").getAsString();
            movie.year = obj.get("Year").getAsString();
            movie.director = obj.get("Director").getAsString();
            movie.genre = obj.get("Genre").getAsString();
            return movie;

        } catch (Exception e) {
            System.out.println("OMDb error: " + e.getMessage());
            return null;
        }
    }
}
