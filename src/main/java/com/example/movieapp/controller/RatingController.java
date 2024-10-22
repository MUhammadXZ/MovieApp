package com.example.movieapp.controller;

import com.example.movieapp.dto.RatingDto;
import com.example.movieapp.model.Rating;
import com.example.movieapp.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody RatingDto ratingDto) {
        Rating createdRating = ratingService.createRating(ratingDto);
        return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
    }
}
