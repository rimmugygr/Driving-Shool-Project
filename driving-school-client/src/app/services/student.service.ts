import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Student} from '../model/Student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  getAllStudent(): Observable<any> {
    return this.http.get('/server/api/student');
  }

  getStudent(studentId: number): Observable<any> {
    return this.http.get('/server/api/student/' + studentId);
  }

  addStudent(student: Student): Observable<any>  {
    return this.http.post(
      '/server/api/student',
      JSON.stringify(student),
      {headers : new HttpHeaders({'Content-Type': 'application/json'})}
    );
  }

  deleteStudent(studentId: number): Observable<any>  {
    return this.http.delete('/server/api/student/' + studentId);
  }
}
