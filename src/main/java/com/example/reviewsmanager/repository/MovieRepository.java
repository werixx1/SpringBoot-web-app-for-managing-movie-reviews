package com.example.reviewsmanager.repository;

import com.example.reviewsmanager.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>
{
    public Movie findMovieByTitle(String title);
}
