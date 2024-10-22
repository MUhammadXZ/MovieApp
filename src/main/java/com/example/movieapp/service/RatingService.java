package com.example.movieapp.service;

import com.example.movieapp.dto.RatingDto;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Rating;
import com.example.movieapp.model.User;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.RatingRepository;
import com.example.movieapp.repository.UserRepository;
import com.example.movieapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    public Rating createRating(RatingDto ratingDto) {
        Movie movie = movieRepository.findById(ratingDto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + ratingDto.getMovieId()));
        User user = userRepository.findById(ratingDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + ratingDto.getUserId()));

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setUser(user);
        rating.setValue(ratingDto.getValue());

        return ratingRepository.save(rating);
    }
}
