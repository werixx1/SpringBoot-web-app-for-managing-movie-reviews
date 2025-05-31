package com.example.reviewsmanager.repository;

import com.example.reviewsmanager.model.Genre;
import com.example.reviewsmanager.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieByTitle(String title);

    List<Movie> findMoviesByGenre(Genre genre);

    // STATISTICS
    // find movie with best reviews (by average nums of stars)
    @Query("""
    SELECT m
    FROM Movie m
    JOIN m.reviews r
    WHERE m.genre = :genre
    GROUP BY m
    ORDER BY AVG(r.numberOfStars) DESC
    """)
    Movie findBestRatedMovieByGenreOrderByAverageStarsDesc(@Param("genre") Genre genre);

    // find movie with worst reviews
    @Query("""
    SELECT m
    FROM Movie m
    JOIN m.reviews r
    WHERE m.genre = :genre
    GROUP BY m
    ORDER BY AVG(r.numberOfStars) ASC
    """)
    Movie findWorstRatedMovieByGenreOrderByAverageStarsAsc(@Param("genre") Genre genre);

    @Query(""" 
    SELECT m
    FROM Movie m
    JOIN m.reviews r
    GROUP BY m
    ORDER BY COUNT(r) DESC
    """)
    Movie findMovieWithMostReviews();
  }
