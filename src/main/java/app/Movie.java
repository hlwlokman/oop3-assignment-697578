package app;

import java.util.*;

public class Movie {
    public String title;
    public String year;
    public String director;
    public String genre;
    public List<String> similarMovies = new ArrayList<>();
    public List<String> imagePaths = new ArrayList<>();
    public boolean watched = false;
    public int rating = 0;
}
