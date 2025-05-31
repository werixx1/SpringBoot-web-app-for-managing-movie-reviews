package com.example.reviewsmanager.controller;

import com.example.reviewsmanager.dto.ReviewDTO;
import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.model.Review;
import com.example.reviewsmanager.model.User;
import com.example.reviewsmanager.repository.ReviewRepository;
import com.example.reviewsmanager.service.MovieService;
import com.example.reviewsmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
@RequestMapping("/reviews")
public class ReviewPageController
{
    @Autowired
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final MovieService movieService;
    public ReviewPageController(ReviewRepository reviewRepository, UserService userService,
                                MovieService movieService)
    {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.movieService = movieService;
    }

    @GetMapping({"", "/"})
    public String getReviews(Model model)
    {
        var reviews = reviewRepository.findAll();
        model.addAttribute("reviews", reviews);
        return "reviews/index";
    }

    @GetMapping("/create")
    public String createReview(Model model)
    {
        ReviewDTO reviewDTO = new ReviewDTO();
        List<Movie> movies = movieService.getAllMovies();
        List<User> users = userService.getAllUsers();
        model.addAttribute("movies", movies);
        model.addAttribute("users", users);
        model.addAttribute("reviewDTO", reviewDTO);
        return "reviews/create";
    }

    @PostMapping("/create")
    public String createReview(ReviewDTO reviewDTO, BindingResult bindingResult)
    {
        Review review = new Review();
        review.setTitle(reviewDTO.getTitle());
        review.setTextReview(reviewDTO.getTextReview());
        review.setNumberOfStars(reviewDTO.getNumberOfStars());
        review.setPostedOn(reviewDTO.getPostedOn());

        Movie movie = movieService.getMovieById(reviewDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        User user = userService.getUserById(reviewDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        review.setMovie(movie);
        review.setAuthor(user);

        reviewRepository.save(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit")
    public String editReview(Model model, @RequestParam Long id)
    {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null)
        {
            return "redirect:/reviews";
        }
        ReviewDTO reviewDTO = new ReviewDTO();
        // there is no ability to change username or movie that review is for because
        // it would go against website logic
        review.setTitle(reviewDTO.getTitle());
        review.setTextReview(reviewDTO.getTextReview());
        review.setNumberOfStars(reviewDTO.getNumberOfStars());

        model.addAttribute("reviewDTO", reviewDTO);
        model.addAttribute("review", review);
        return "reviews/edit";
    }

    @PostMapping("/edit")
    public String editReview(Model model, @RequestParam Long id,
                           ReviewDTO reviewDTO, BindingResult bindingResult)
    {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null)
        {
            return "redirect:/reviews";
        }
        model.addAttribute("review", review);

        review.setTitle(reviewDTO.getTitle());
        review.setTextReview(reviewDTO.getTextReview());
        review.setNumberOfStars(reviewDTO.getNumberOfStars());
        reviewRepository.save(review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete")
    public String deleteReview(@RequestParam Long id)
    {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review != null)
        {
            reviewRepository.delete(review);
        }
        return "redirect:/users";
    }
}
