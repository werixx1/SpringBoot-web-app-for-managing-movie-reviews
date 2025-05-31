package com.example.reviewsmanager.dto;
import com.example.reviewsmanager.model.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO
{
    private String title;
    private Genre genre;
    private String mainDirector;
    private Integer releaseYear;
}
