import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private apiUrl: string = 'http://www.omdbapi.com/?apikey=b077f0b8';

  constructor(private http: HttpClient) {}
  rateMovie(movieId: string, rating: number): Observable<any> {
      return this.http.post(`${this.apiUrl}/movies/${movieId}/rate`, { rating });
    }

  getMovies(): Observable<any> {
    return this.http.get(`${this.apiUrl}&s=movie`);
  }

  getMovieById(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}&i=${id}`);
  }
}
