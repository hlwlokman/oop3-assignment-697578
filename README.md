# 🎬 Movie Watchlist (OOP3 Java Assignment)

This is my Java application for the OOP3 assignment.  
It gets movie details from two APIs and saves everything in a local database.

---

## ✅ What This App Does

1. Ask the user to enter a movie title in the console
2. Get basic info from **OMDb API** (title, year, director, genre)
3. Get extra info from **TMDB API** (3 images and similar movies)
4. Save the 3 images to a folder called `images/`
5. Save all movie info to a local **SQLite database**
6. Start a REST API so users can:
   - See all movies (`GET /movies`)
   - Update watched flag (`POST /movies/:id/watched`)
   - Update rating (`POST /movies/:id/rating?value=4`)
   - Delete a movie (`DELETE /movies/:id`)

---

## 🧪 How to Run It

1. **Get 2 API keys**:
   - OMDb: https://www.omdbapi.com/apikey.aspx
   - TMDB: https://developer.themoviedb.org

2. **My API keys in the code**:
   - `OmdbClient.java` → `API_KEY = "dbf55059"`
   - `TmdbClient.java` → `API_KEY = "8b3b48a508470270060bd462d86d2a10"`


Tech Used :
    Java 17
    Maven
    SQLite
    Gson (JSON parser)
    SparkJava (REST API framework)
    OMDb & TMDB APIs

