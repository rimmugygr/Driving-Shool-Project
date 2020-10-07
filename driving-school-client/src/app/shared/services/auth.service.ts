import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoginRequest, LoginResponse} from '../model/Auth';

const AUTH_API = '/server/api/auth/';

const httpHeaders = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  postLogin(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(AUTH_API + 'signin', loginRequest, httpHeaders);
  }

  logout(): void {
    window.location.reload();
  }
}
