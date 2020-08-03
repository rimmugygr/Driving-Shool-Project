import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Teacher} from '../model/Teacher';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  constructor(private http: HttpClient) { }

  getAllTeacher(): Observable<any> {
    return this.http.get('/server/api/teacher');
  }

  addTeacher(teacher: Teacher): Observable<any>  {
    return this.http.post(
      '/server/api/teacher',
      JSON.stringify(teacher),
      {headers : new HttpHeaders({'Content-Type': 'application/json'})}
    );
  }

  deleteTeacher(teacherId: number): Observable<any>  {
    return this.http.delete('/server/api/teacher/' + teacherId);
  }
}
