import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Teacher} from '../model/Teacher';

const TEACHER_API = '/server/api/manage/teachers';
const httpHeaders = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  constructor(private http: HttpClient) { }

  getAllTeacher(): Observable<any> {
    return this.http.get(TEACHER_API);
  }

  getTeacher(teacherId: number): Observable<any> {
    return this.http.get(TEACHER_API + '/' + teacherId);
  }

  addTeacher(teacher: Teacher): Observable<any>  {
    return this.http.post(
      TEACHER_API,
      JSON.stringify(teacher),
      httpHeaders
    );
  }

  patchTeacher(teacher: Teacher, teacherId: number): Observable<any>  {
    return this.http.put(
      TEACHER_API + '/' + teacherId,
      JSON.stringify(teacher),
      httpHeaders
    );
  }
  deleteTeacher(teacherId: number): Observable<any>  {
    return this.http.delete(TEACHER_API + '/' + teacherId);
  }
}
