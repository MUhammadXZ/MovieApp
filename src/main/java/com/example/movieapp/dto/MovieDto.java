package com.example.movieapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDto {
    private String title;
    private String director;
    private String genre;
    private int releaseYear;
    private String posterUrl;
    private String description;
    private LocalDate releaseDate;
    private Double rating;

}
