import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Teacher} from '../model/Teacher';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  link = '/server/api/manage/teachers';

  constructor(private http: HttpClient) { }

  getAllTeacher(): Observable<any> {
    return this.http.get(this.link);
  }

  getTeacher(teacherId: number): Observable<any> {
    return this.http.get(this.link + '/' + teacherId);
  }

  addTeacher(teacher: Teacher): Observable<any>  {
    return this.http.post(
      this.link,
      JSON.stringify(teacher),
      {headers : new HttpHeaders({'Content-Type': 'application/json'})}
    );
  }

  patchTeacher(teacher: Teacher, teacherId: number): Observable<any>  {
    return this.http.put(
      this.link + '/' + teacherId,
      JSON.stringify(teacher),
      {headers : new HttpHeaders({'Content-Type': 'application/json'})}
    );
  }
  deleteTeacher(teacherId: number): Observable<any>  {
    return this.http.delete(this.link + '/' + teacherId);
  }
}
