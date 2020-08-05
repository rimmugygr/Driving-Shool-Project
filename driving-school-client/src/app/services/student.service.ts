import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Student} from '../model/Student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  link = '/server/api/manage/students';

  constructor(private http: HttpClient) { }

  getAllStudent(): Observable<any> {
    return this.http.get(this.link);
  }

  getStudent(studentId: number): Observable<any> {
    return this.http.get(this.link + '/' + studentId);
  }

  addStudent(student: Student): Observable<any>  {
    return this.http.post(
      this.link,
      JSON.stringify(student),
      {headers : new HttpHeaders({'Content-Type': 'application/json'})}
    );
  }

  patchStudent(student: Student, studentId: number): Observable<any>  {
    return this.http.patch(
      this.link + '/' + studentId,
      JSON.stringify(student),
      {headers : new HttpHeaders({'Content-Type': 'application/json'})}
    );
  }
  deleteStudent(studentId: number): Observable<any>  {
    return this.http.delete(this.link + '/' + studentId);
  }
}
