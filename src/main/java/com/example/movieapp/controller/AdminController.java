package com.example.movieapp.controller;

import com.example.movieapp.dto.MovieDto;
import com.example.movieapp.model.Movie;
import com.example.movieapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private MovieService movieService;

    // Add a new movie
    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDto movieDto) {
        Movie newMovie = movieService.addMovie(movieDto);
        return ResponseEntity.ok(newMovie);
    }

    // Remove a movie
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> removeMovie(@PathVariable Long id) {
        movieService.removeMovie(id);
        return ResponseEntity.noContent().build();
    }

    // Get all movies
    @GetMapping("/movies")
    public ResponseEntity<Page<Movie>> getAllMovies(Pageable pageable) {
        Page<Movie> movies = movieService.getAllMovies(pageable);
        return ResponseEntity.ok(movies);
    }
}
