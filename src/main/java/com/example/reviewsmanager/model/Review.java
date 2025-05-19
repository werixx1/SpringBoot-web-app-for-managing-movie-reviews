package com.example.reviewsmanager.model;

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
}
