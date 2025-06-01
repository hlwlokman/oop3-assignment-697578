package app;

import java.sql.*;
import java.util.*;

public class MovieDatabase {
    static {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:movies.db")) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS movies (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, year TEXT, director TEXT, genre TEXT, " +
                "images TEXT, similar TEXT, watched BOOLEAN, rating INTEGER)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(Movie m) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:movies.db")) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO movies (title, year, director, genre, images, similar, watched, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, m.title);
            ps.setString(2, m.year);
            ps.setString(3, m.director);
            ps.setString(4, m.genre);
            ps.setString(5, String.join(",", m.imagePaths));
            ps.setString(6, String.join(",", m.similarMovies));
            ps.setBoolean(7, m.watched);
            ps.setInt(8, m.rating);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Movie> list(int page, int limit) {
        List<Movie> movies = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:movies.db")) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM movies LIMIT ? OFFSET ?");
            ps.setInt(1, limit);
            ps.setInt(2, (page - 1) * limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movie m = new Movie();
                m.title = rs.getString("title");
                m.year = rs.getString("year");
                m.director = rs.getString("director");
                m.genre = rs.getString("genre");
                m.imagePaths = Arrays.asList(rs.getString("images").split(","));
                m.similarMovies = Arrays.asList(rs.getString("similar").split(","));
                m.watched = rs.getBoolean("watched");
                m.rating = rs.getInt("rating");
                movies.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static void updateWatched(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:movies.db")) {
            PreparedStatement ps = conn.prepareStatement("UPDATE movies SET watched = 1 WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateRating(int id, int rating) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:movies.db")) {
            PreparedStatement ps = conn.prepareStatement("UPDATE movies SET rating = ? WHERE id = ?");
            ps.setInt(1, rating);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:movies.db")) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM movies WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
