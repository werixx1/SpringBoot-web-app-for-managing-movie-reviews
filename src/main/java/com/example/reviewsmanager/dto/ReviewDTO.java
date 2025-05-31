package com.example.reviewsmanager.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDTO
{
    private String title;
    private String textReview;
    private Integer numberOfStars;
    private LocalDateTime postedOn;
    private Long movieId;
    private Long userId;
}
