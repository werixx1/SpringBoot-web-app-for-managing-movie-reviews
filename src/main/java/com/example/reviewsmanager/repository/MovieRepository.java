package com.example.reviewsmanager.repository;

import com.example.reviewsmanager.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>
{

}
