package com.example.reviewsmanager.controller;

import com.example.reviewsmanager.model.Genre;
import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController
{
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)
    {
        Movie savedMovie = movieService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id)
    {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent())
        {
            return ResponseEntity.ok(movie.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/title/{movieTitle}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable("movieTitle") String title)
    {
        final Movie movieByTitle = movieService.getMovieByTitle(title);
        if (movieByTitle != null)
        {
            return ResponseEntity.ok(movieByTitle);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable("genre") Genre genre)
    {
        List<Movie> movies = movieService.getMoviesByGenre(genre);
        if (movies.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovieById(@PathVariable Long id, @RequestBody Movie newMovieData)
    {
        Movie updateMovie = movieService.updateMovie(id, newMovieData);

        if (updateMovie != null)
        {
            return ResponseEntity.ok(updateMovie);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovieById(@PathVariable Long id)
    {
        boolean deleted = movieService.deleteMovieById(id);
        if (deleted)
        {
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Movie> getAllMovies()
    {
        return movieService.getAllMovies();
    }

    @GetMapping("/best-rated-movie/genre/{genre}")
    public Movie getBestRatedMovieByGenre(@PathVariable Genre genre)
    {
        return movieService.getBestRatedMovieByGenre(genre);
    }

    @GetMapping("/worst-rated-movie/genre/{genre}")
    public Movie getWorstRatedMovieByGenre(@PathVariable Genre genre)
    {
        return movieService.getWorstRatedMovieByGenre(genre);
    }

    @GetMapping("/movie-with-most-reviews")
    public Movie getMovieWithMostReviews()
    {
        return movieService.getMovieWithMostReviews();
    }
}

