import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
  movies: any[] = [];
  apiKey: string = 'b077f0b8';
  backendUrl: string = 'http://localhost:8080/api/movies';
  movieForm: FormGroup;

  constructor(private http: HttpClient, private fb: FormBuilder) {
    this.movieForm = this.fb.group({
      title: ['', Validators.required],
      year: ['', Validators.required],
      genre: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.loadMovies();
  }

  loadMovies() {
    this.http.get(`http://www.omdbapi.com/?apikey=${this.apiKey}&s=movie`).subscribe((data: any) => {
      this.movies = data.Search || [];
    });
  }

  addMovie() {
    if (this.movieForm.valid) {
      const movieData = this.movieForm.value;
      this.http.post(this.backendUrl, movieData).subscribe(response => {
        console.log("Movie added successfully:", response);
        this.loadMovies();
        this.movieForm.reset();
      }, error => {
        console.error("Error adding movie:", error);
      });
    } else {
      console.error("Form is invalid");
    }
  }

  removeMovie(movieId: string) {
    if (confirm("Are you sure you want to remove this movie?")) {
      this.http.delete(`${this.backendUrl}/${movieId}`).subscribe(response => {
        console.log("Movie removed successfully:", response);
        this.loadMovies();
      }, error => {
        console.error("Error removing movie:", error);
      });
    }
  }
}
