package com.example.movieapp.dto;

import lombok.Data;

@Data
public class RatingDto {
    private Long movieId;
    private Long userId;
    private int value;
}
