package com.example.movieapp.service;

import com.example.movieapp.dto.MovieDto;
import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;


    public Page<Movie> getAllMovies(Pageable pageable) {
        return movieRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
    }

    public Movie addMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDirector(movieDto.getDirector());
        movie.setGenre(movieDto.getGenre());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setPosterUrl(movieDto.getPosterUrl());
        movieRepository.save(movie);
        return movie;
    }

    public void removeMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        movieRepository.delete(movie);
    }

    public List<Movie> searchMovies(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
    @Transactional
    public List<Movie> addMovies(List<MovieDto> movieDtos) {
        List<Movie> movies = movieDtos.stream().map(this::convertToEntity).toList();
        return movieRepository.saveAll(movies);
    }

    @Transactional
    public void deleteMovies(List<Long> movieIds) {
        movieRepository.deleteAllById(movieIds);
    }

    private Movie convertToEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setRating(movieDto.getRating());
        return movie;
    }
}
