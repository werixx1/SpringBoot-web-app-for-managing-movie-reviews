package com.example.reviewsmanager.service;

import com.example.reviewsmanager.model.Genre;
import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
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

    public Optional<Movie> getMovieById(Long id)
    {
        return movieRepository.findById(id);
    }

    public List<Movie> getMoviesByGenre(Genre genre)
    {
        return movieRepository.findMoviesByGenre(genre);
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
        throw new EntityNotFoundException("Movie with id " + id + " not found");
    }

    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }

    public Movie getBestRatedMovieByGenre(Genre genre)
    {
        return movieRepository.findBestRatedMovieByGenreOrderByAverageStarsDesc(genre);
    }

    public Movie getWorstRatedMovieByGenre(Genre genre)
    {
        return movieRepository.findWorstRatedMovieByGenreOrderByAverageStarsAsc(genre);
    }

    public Movie getMovieWithMostReviews()
    {
        List<Movie> movies = movieRepository.findMovieWithMostReviews(PageRequest.of(0, 1));
        return movies.isEmpty() ? null : movies.get(0); // if theres more than one movie with
                                                       // the same amount of reviews it returns the first one
    }

}
