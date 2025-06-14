package app;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.List;

public class ImageDownloader {
    public static void downloadImages(Movie movie) {
        try {
            //  Do nothing if no images available
            List<String> images = movie.imagePaths;
            if (images == null || images.isEmpty()) {
                System.out.println("ℹ️ No images found to download.");
                return;
            }
            // Create folder if needed
            Files.createDirectories(Paths.get("images")); 

            for (int i = 0; i < Math.min(3, images.size()); i++) {
                String imgUrl = images.get(i);
                URL url = new URL(imgUrl);
                InputStream in = url.openStream();
                String filename = "images/" + movie.title.replaceAll(" ", "_") + "_" + i + ".jpg";
                Files.copy(in, Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);
                in.close();
                images.set(i, filename); 
            }
        } catch (Exception e) {
            System.out.println(" Image download error: " + e.getMessage());
        }
    }
}
