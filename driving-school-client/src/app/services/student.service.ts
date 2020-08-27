import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Student} from '../model/Student';

const STUDENT_API = '/server/api/manage/students';
const httpHeaders = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  getAllStudent(): Observable<any> {
    return this.http.get(STUDENT_API);
  }

  getStudent(studentId: number): Observable<any> {
    return this.http.get(STUDENT_API + '/' + studentId);
  }

  addStudent(student: Student): Observable<any>  {
    return this.http.post(
      STUDENT_API,
      JSON.stringify(student),
      httpHeaders
    );
  }

  patchStudent(student: Student, studentId: number): Observable<any>  {
    return this.http.put(
      STUDENT_API + '/' + studentId,
      JSON.stringify(student),
      httpHeaders
    );
  }
  deleteStudent(studentId: number): Observable<any>  {
    return this.http.delete(STUDENT_API + '/' + studentId);
  }
}
