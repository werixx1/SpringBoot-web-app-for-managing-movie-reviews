package com.example.reviewsmanager.service;

import com.example.reviewsmanager.dto.RaportDTO;
import com.example.reviewsmanager.model.Genre;
import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.repository.MovieRepository;
import com.example.reviewsmanager.repository.ReviewRepository;
import com.example.reviewsmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RaportService
{
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public RaportService(UserRepository userRepository, ReviewRepository reviewRepository,
                         MovieRepository movieRepository)
    {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    public RaportDTO getDatabaseRaport()
    {
        long users = userRepository.count();
        long reviews = reviewRepository.count();
        long movies = movieRepository.count();

        // calculate how many movies are for each genre
        Map<Genre, Long> genreFrequency = movieRepository.findAll().stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.counting()));

        return new RaportDTO(users, reviews, movies, genreFrequency);
    }
}
