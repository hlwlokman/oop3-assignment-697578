package app;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class ImageDownloader {
    public static void downloadImages(Movie movie) {
        try {
            Files.createDirectories(Paths.get("images")); // ✅ Create folder if not exist

            int count = 0;
            for (String imgUrl : movie.imagePaths) {
                if (count >= 3) break;
                URL url = new URL(imgUrl);
                InputStream in = url.openStream();
                String filename = "images/" + movie.title.replaceAll(" ", "_") + "_" + count + ".jpg";
                Files.copy(in, Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);
                in.close();
                movie.imagePaths.set(count, filename);
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
