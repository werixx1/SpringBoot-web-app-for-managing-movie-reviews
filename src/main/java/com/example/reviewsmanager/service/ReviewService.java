package com.example.reviewsmanager.service;

import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.model.Review;
import com.example.reviewsmanager.repository.MovieRepository;
import com.example.reviewsmanager.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService
{
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository)
    {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }
    public Review addReview(Review review)
    {
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByUserId(Long id)
    {
       return reviewRepository.findReviewsByAuthorId(id);
    }

    public List<Review> getReviewsByMovieId(Long id)
    {
        return reviewRepository.findReviewsByMovieId(id);
    }

    public Optional<Review> getReviewByReviewId(Long id)
    {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsByMovieTitle(String title)
    {
        Movie movie = movieRepository.findMovieByTitle(title);
        if (movie != null)
        {
            return reviewRepository.findReviewsByMovieTitle(movie);
        }
        else
        {
            return Collections.emptyList();
        }
    }

    public boolean deleteReview(Long id)
    {
        if (reviewRepository.existsById(id))
        {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Review updateReview(Long id, Review newReviewData)
    {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent())
        {
            Review review = optionalReview.get();
            review.setTitle(newReviewData.getTitle());
            review.setNumberOfStars(newReviewData.getNumberOfStars());
            review.setTextReview(newReviewData.getTextReview());
            review.setPostedOn(newReviewData.getPostedOn());
            return reviewRepository.save(review);
        }
        throw new EntityNotFoundException("Review with id " + id + " not found");
    }

    public List<Review> getAllReviews()
    {
        return reviewRepository.findAll();
    }

    public Review getLatestReview()
    {
        return reviewRepository.findFirstByOrderByPostedOnDesc();
    }
}
