package com.example.movieapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private int releaseYear;

    private String posterUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(nullable = true)
    private Double rating;
}
