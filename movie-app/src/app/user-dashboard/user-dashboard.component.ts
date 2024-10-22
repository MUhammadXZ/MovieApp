import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
  movies: any[] = [];
  searchQuery: string = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadMovies();
  }

  loadMovies() {
    this.http.get('http://localhost:8080/api/movies').subscribe((data: any) => {
      this.movies = data || [];
    });
  }

  searchMovies() {
    if (this.searchQuery) {
      this.http.get(`http://localhost:8080/api/movies/search?query=${this.searchQuery}`).subscribe((data: any) => {
        this.movies = data || [];
      });
    } else {
      this.loadMovies();
    }
  }
}
