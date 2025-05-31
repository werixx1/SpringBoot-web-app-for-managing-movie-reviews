package com.example.reviewsmanager.controller;

import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.dto.MovieDTO;
import com.example.reviewsmanager.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MoviePageController
{
    @Autowired
    private final MovieRepository movieRepository;
    public MoviePageController(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    @GetMapping({"", "/"})
    public String getMovies(Model model)
    {
        var movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "movies/index";
    }

    @GetMapping("/create")
    public String createMovie(Model model)
    {
        MovieDTO movieDTO = new MovieDTO();
        model.addAttribute("movieDTO", movieDTO);
        return "movies/create";
    }

    @PostMapping("/create")
    public String createMovie(MovieDTO movieDTO, BindingResult bindingResult)
    {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setMainDirector(movieDTO.getMainDirector());
        movie.setReleaseYear(movieDTO.getReleaseYear());
        movieRepository.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit")
    public String editMovie(Model model, @RequestParam Long id)
    {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null)
        {
            return "redirect:/movies";
        }
        MovieDTO movieDTO = new MovieDTO();
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setMainDirector(movieDTO.getMainDirector());
        movie.setReleaseYear(movieDTO.getReleaseYear());

        model.addAttribute("movieDTO", movieDTO);
        model.addAttribute("movie", movie);

        return "movies/edit";
    }

    @PostMapping("/edit")
    public String editMovie(Model model, @RequestParam Long id,
                            MovieDTO movieDTO, BindingResult bindingResult)
    {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return "redirect:/movies";
        }
        model.addAttribute("movie", movie);

        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setMainDirector(movieDTO.getMainDirector());
        movie.setReleaseYear(movieDTO.getReleaseYear());
        movieRepository.save(movie);

        return "redirect:/movies";
    }

    @GetMapping("/delete")
    public String deleteMovie(@RequestParam Long id)
    {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null)
        {
            movieRepository.delete(movie);
        }
        return "redirect:/movies";
    }
}

