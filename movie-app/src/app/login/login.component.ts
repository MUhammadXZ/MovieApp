import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  login() {
    if (this.loginForm.valid) {
      const loginData = this.loginForm.value;
      this.http.post('http://localhost:8080/api/login', loginData).subscribe(response => {
        console.log("Login successful:", response);
        // Navigate to the admin dashboard or user dashboard based on roles
        this.router.navigate(['/admin-dashboard']); // Adjust this based on your application flow
      }, error => {
        console.error("Login error:", error);
        alert("Login failed. Please check your credentials.");
      });
    } else {
      console.error("Form is invalid");
    }
  }
}
