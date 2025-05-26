package com.example.reviewsmanager.repository;

import com.example.reviewsmanager.model.Genre;
import com.example.reviewsmanager.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>
{
    Movie findMovieByTitle(String title);
    List<Movie> findMoviesByGenre(Genre genre);
}
