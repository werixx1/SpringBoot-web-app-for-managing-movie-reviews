package com.example.reviewsmanager.service;

import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService
{
    @Autowired
    MovieRepository movieRepository;

    public Movie addMovie(Movie movie)
    {
        return movieRepository.save(movie);
    }

    public Movie getMovieByTitle(String title)
    {
        return movieRepository.findMovieByTitle(title);
    }

    public Movie save(Movie movie)
    {
        return movieRepository.save(movie);
    }

    public boolean deleteMovieById(Long id)
    {
        if (movieRepository.existsById(id))
        {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Movie> getMovieById(Long id)
    {
        return movieRepository.findById(id);
    }

    public Movie updateMovie(Long id, Movie newMovieData)
    {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent())
        {
            Movie movie = optionalMovie.get();
            movie.setTitle(newMovieData.getTitle());
            movie.setGenre(newMovieData.getGenre());
            movie.setMainDirector(newMovieData.getMainDirector());
            movie.setReleaseYear(newMovieData.getReleaseYear());

            return movieRepository.save(movie);
        }
        return null;
    }

    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }
}
