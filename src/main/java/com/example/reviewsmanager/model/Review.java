package com.example.reviewsmanager.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String textReview;
    private Integer numberOfStars; // 1-5
    private LocalDateTime postedOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // used when object is saved to the database for the first time
    @PrePersist
    protected void onCreate()
    {
        postedOn = LocalDateTime.now();
    }

    // for sample data
    public Review(String title, String textReview, Integer numberOfStars, User author, Movie movie)
    {
        this.title = title;
        this.textReview = textReview;
        this.numberOfStars = numberOfStars;
        this.author = author;
        this.movie = movie;
    }
}
