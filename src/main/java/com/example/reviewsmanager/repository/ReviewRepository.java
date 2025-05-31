package com.example.reviewsmanager.repository;

import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>
{
    List<Review> findReviewsByMovieId(Long id);
    List<Review> findReviewsByMovieTitle(Movie movie);
    Optional<Review> findById(Long id);
    List<Review> findReviewsByAuthorId(Long id);

    // STATISTICS
    // latest review
    Review findFirstByOrderByPostedOnDesc();
}
