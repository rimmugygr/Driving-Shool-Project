import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {IStudent} from '../model/Student';
import {tap} from 'rxjs/operators';

const STUDENT_API = '/server/api/manage/students';
const httpHeaders = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  getAllStudentsResponse(): Observable<IStudent[]> {
    return this.http.get<IStudent[]>(STUDENT_API).pipe(
      tap(x => console.log(JSON.stringify(x[0])))
    );
  }

  getStudent(studentId: number): Observable<IStudent> {
    return this.http.get<IStudent>(STUDENT_API + '/' + studentId);
  }

  postStudent(student: IStudent): Observable<IStudent>  {
    return this.http.post<IStudent>(STUDENT_API, JSON.stringify(student), httpHeaders);
  }

  patchStudent(student: IStudent): Observable<IStudent>  {
    return this.http.put<IStudent>(STUDENT_API + '/' + student.id, JSON.stringify(student), httpHeaders);
  }
  deleteStudent(student: IStudent): Observable<IStudent>  {
    return this.http.delete<IStudent>(STUDENT_API + '/' + student.id);
  }
}
