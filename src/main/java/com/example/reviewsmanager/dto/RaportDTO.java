package com.example.reviewsmanager.dto;

import com.example.reviewsmanager.model.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class RaportDTO
{
    private long totalUsers;
    private long totalReviews;
    private long totalMovies;
    private Map<Genre, Long> genreFrequency;
    // private double averageRating;

    public RaportDTO(long totalUsers, long totalReviews, long totalMovies, Map<Genre, Long> genreFrequency)
    {
        this.totalUsers = totalUsers;
        this.totalReviews = totalReviews;
        this.totalMovies = totalMovies;
        this.genreFrequency = genreFrequency;
    }
}
