import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth/login';
  private token: string | null = localStorage.getItem('token');

  constructor(private http: HttpClient) { }

  isAuthenticated(): boolean {
    return !!this.token;
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { username, password });
  }

  logout(): void {
    localStorage.removeItem('token');
    this.token = null;
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token);
    this.token = token;
  }
}
