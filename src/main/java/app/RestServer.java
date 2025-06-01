package app;

import static spark.Spark.*;
import com.google.gson.*;
import java.util.*;

public class RestServer {
    public static void start() {
        port(4567);
        Gson gson = new Gson();

        get("/movies", (req, res) -> {
            int page = Integer.parseInt(req.queryParams("page"));
            int limit = Integer.parseInt(req.queryParams("limit"));
            return gson.toJson(MovieDatabase.list(page, limit));
        });

        post("/movies/:id/watched", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            MovieDatabase.updateWatched(id);
            return "Updated watched";
        });

        post("/movies/:id/rating", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            int rating = Integer.parseInt(req.queryParams("value"));
            MovieDatabase.updateRating(id, rating);
            return "Updated rating";
        });

        delete("/movies/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            MovieDatabase.delete(id);
            return "Deleted";
        });
    }
}
