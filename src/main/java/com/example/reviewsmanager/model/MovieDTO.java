package com.example.reviewsmanager.model;

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
