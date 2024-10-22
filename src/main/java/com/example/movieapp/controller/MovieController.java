package com.example.movieapp.controller;

import com.example.movieapp.dto.MovieDto;
import com.example.movieapp.model.Movie;
import com.example.movieapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public Page<Movie> getAllMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return movieService.getAllMovies((Pageable) PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addMovie(@RequestBody MovieDto movieDto) {
        movieService.addMovie(movieDto);
        return ResponseEntity.ok("Movie added successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> removeMovie(@PathVariable Long id) {
        movieService.removeMovie(id);
        return ResponseEntity.ok("Movie removed successfully");
    }

    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam("title") String title) {
        return movieService.searchMovies(title);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/batch")
    public ResponseEntity<List<Movie>> addMovies(@RequestBody List<MovieDto> movieDtos) {
        List<Movie> addedMovies = movieService.addMovies(movieDtos);
        return ResponseEntity.ok(addedMovies);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteMovies(@RequestBody List<Long> movieIds) {
        movieService.deleteMovies(movieIds);
        return ResponseEntity.noContent().build();
    }
}
