package app;

public class MovieMerger {
    public static Movie merge(Movie omdb, Movie tmdb) {
        Movie m = new Movie();
        m.title = omdb.title;
        m.year = omdb.year;
        m.director = omdb.director;
        m.genre = omdb.genre;
        m.similarMovies = tmdb.similarMovies;
        m.imagePaths = tmdb.imagePaths;
        return m;
    }
}
