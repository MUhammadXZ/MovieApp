import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../movie.service';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css']
})
export class MovieDetailComponent implements OnInit {
  movie: any;
  rating: number = 0;

  constructor(
    private route: ActivatedRoute,
    private movieService: MovieService
  ) { }

  ngOnInit(): void {
    const movieId = this.route.snapshot.paramMap.get('id');
    this.getMovieDetails(movieId);
  }

  getMovieDetails(id: string | null): void {
    if (id) {
      this.movieService.getMovieById(id).subscribe(data => {
        this.movie = data;
      });
    }
  }

  rateMovie(movieId: string): void {
    if (this.rating) {
      this.movieService.rateMovie(movieId, this.rating).subscribe(
        response => {
          console.log('Rating submitted:', response);
        },
        error => {
          console.error('Error submitting rating:', error);
        }
      );
    } else {
      console.log('Please select a rating before submitting.');
    }
  }
}
