package app;

import java.util.Scanner;
import java.util.concurrent.*;

public class MovieApp {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter movie title: ");
        String title = sc.nextLine();
        sc.close();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Movie> omdbFuture = executor.submit(() -> OmdbClient.fetchMovie(title));
        Future<Movie> tmdbFuture = executor.submit(() -> TmdbClient.fetchExtras(title));

        try {
            Movie movieOmdb = omdbFuture.get();
            Movie movieTmdb = tmdbFuture.get();

            // Merge
            Movie movie = MovieMerger.merge(movieOmdb, movieTmdb);

            ImageDownloader.downloadImages(movie);
            MovieDatabase.save(movie);

            System.out.println("✅ Movie added to database.");

            RestServer.start();
            HtmlRoute.setup(); // Optional: also start REST server

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
