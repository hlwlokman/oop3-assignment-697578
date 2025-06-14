package app;

public class MovieApp {
    public static void start() {
        System.out.println("🎬 Movie Watchlist Started...");
        System.out.print("Enter movie title: ");
        String title = System.console().readLine();

        // ✅ Fetch data from both APIs
        Movie movieOmdb = OmdbClient.fetchMovie(title);
        Movie movieTmdb = TmdbClient.fetchExtras(title);

        // ✅ Check if data was successfully fetched
        if (movieOmdb == null || movieTmdb == null) {
            System.out.println("❌ Could not fetch movie data. Check title or API keys.");
            return;
        }

        // ✅ Merge both movie data
        Movie movie = MovieMerger.merge(movieOmdb, movieTmdb);

        // ✅ Download 3 images only if available
        ImageDownloader.downloadImages(movie);

        // ✅ Save to SQLite database
        MovieDatabase.save(movie);

        System.out.println("✅ Movie added to database.");
    }
}
