package com.example.reviewsmanager.controller;

import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.model.Review;
import com.example.reviewsmanager.service.MovieService;
import com.example.reviewsmanager.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController
{
    private final ReviewService reviewService;
    private final MovieService movieService;

    @Autowired
    public ReviewController(ReviewService reviewService, MovieService movieService)
    {
        this.reviewService = reviewService;
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review)
    {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUser(@PathVariable Long userId)
    {
        return reviewService.getReviewsByUserId(userId);
    }

    @GetMapping("/movie/id/{movieId}")
    public List<Review> getReviewByMovie(@PathVariable Long movieId)
    {
        return reviewService.getReviewsByMovieId(movieId);
    }

    @GetMapping("/{id}")
    public Optional<Review> getReviewByReviewID(@PathVariable Long id)
    {
        return reviewService.getReviewByReviewId(id);
    }

    @GetMapping("/movie/title/{title}")
    public ResponseEntity<List<Review>> getReviewsByMovie(@PathVariable String title)
    {
        List<Review> reviews = reviewService.getReviewsByMovieTitle(title);
        if (reviews != null)
        {
            return ResponseEntity.ok(reviews);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReviewById(@PathVariable Long id)
    {
        boolean deleted = reviewService.deleteReview(id);
        if (deleted)
        {
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReviewById(@PathVariable Long id, @RequestBody Review newReviewData)
    {
        Review updateReview = reviewService.updateReview(id, newReviewData);

        if (updateReview != null)
        {
            return ResponseEntity.ok(updateReview);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Review> getAllReviews()
    {
        return reviewService.getAllReviews();
    }
}
