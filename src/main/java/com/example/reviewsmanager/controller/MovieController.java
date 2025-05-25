package com.example.reviewsmanager.controller;

import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.repository.MovieRepository;
import com.example.reviewsmanager.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
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
}

