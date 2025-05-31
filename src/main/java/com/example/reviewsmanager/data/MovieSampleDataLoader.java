package com.example.reviewsmanager.data;

import com.example.reviewsmanager.model.Genre;
import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class MovieSampleDataLoader implements CommandLineRunner
{
    private final MovieRepository movieRepository;

    public MovieSampleDataLoader(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        if (movieRepository.count() == 0)
        {
            Movie movie1 = new Movie("Everything Everywhere All At Once", Genre.ACTION,
                    "Dan Kwan", 2022);
            Movie movie2 = new Movie("Guardians Of The Galaxy 3", Genre.ACTION,
                    "James Gunn", 2023);
            Movie movie3 = new Movie("Better Days", Genre.DRAMA,
                    "Derek Tsang", 2019);
            Movie movie4 = new Movie("STAR WARS: Episode IV - New Hope", Genre.SCIFI,
                    "George Lucas", 1977);
            Movie movie5 = new Movie("STAR WARS: Episode V - The Empire Strikes Back", Genre.SCIFI,
                    "George Lucas", 1980);
            Movie movie6 = new Movie("STAR WARS: Episode III - Revenge of the Sith", Genre.SCIFI,
                    "George Lucas", 2005);
            movieRepository.save(movie1);
            movieRepository.save(movie2);
            movieRepository.save(movie3);
            movieRepository.save(movie4);
            movieRepository.save(movie5);
            movieRepository.save(movie6);
            System.out.println("Sample movies added to database");
        }
        else
        {
            System.out.println("Movies already present in database, skipping sample data load.");
        }
    }
}
