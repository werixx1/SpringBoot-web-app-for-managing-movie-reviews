package com.example.reviewsmanager.data;

import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.model.Review;
import com.example.reviewsmanager.model.User;
import com.example.reviewsmanager.repository.MovieRepository;
import com.example.reviewsmanager.repository.ReviewRepository;
import com.example.reviewsmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Order(3)
public class ReviewSampleDataLoader implements CommandLineRunner
{
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public ReviewSampleDataLoader(ReviewRepository reviewRepository, UserRepository userRepository,
                                  MovieRepository movieRepository)
    {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }


    @Override
    public void run(String... args) throws Exception
    {
        if (reviewRepository.count() == 0)
        {
            User user1 = userRepository.findByUsername("maklowicz").orElseThrow(()
                    -> new IllegalStateException("User not found"));
            User user2 = userRepository.findByUsername("podsiadlo").orElseThrow(()
                    -> new IllegalStateException("User not found"));
            User user3 = userRepository.findByUsername("moviehater1").orElseThrow(()
                    -> new IllegalStateException("User not found"));
            User user4 = userRepository.findByUsername("movielover2").orElseThrow(()
                    -> new IllegalStateException("User not found"));
            Movie movie1 = movieRepository.findMovieByTitle("Everything Everywhere All At Once");
            if (movie1 == null)
            {
                throw new IllegalStateException("Movie not found");
            }
            Movie movie2 = movieRepository.findById(4L).orElse(null);
            if (movie2 == null)
            {
                throw new IllegalStateException("Movie not found");
            }

            Review review1 = new Review("I am a changed man", "Rocks made me bawl",
                    5, user1, movie1);
            Review review2 = new Review("Meh", "I guess it was a movie? Dunno, I started" +
                    " sleeping at title screen",
                    2, user2, movie1);
            Review review3 = new Review("epic", "the powerpoint transitions never get old",
                    5, user4, movie2);
            Review review4 = new Review("ok", "im just here for obiwan anyway",
                    5, user3, movie2);

            reviewRepository.save(review1);
            reviewRepository.save(review2);
            reviewRepository.save(review3);
            reviewRepository.save(review4);
            System.out.println("Sample movies added to database");
        }
        else
        {
            System.out.println("Reviews already present in database, skipping sample data load.");
        }
    }

}
