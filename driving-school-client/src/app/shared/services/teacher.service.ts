import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ITeacher, Teacher} from '../model/Teacher';

const TEACHER_API = '/server/api/manage/teachers';
const httpHeaders = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  constructor(private http: HttpClient) { }

  getAllTeachersResponse(): Observable<ITeacher[]> {
    return this.http.get<ITeacher[]>(TEACHER_API);
  }

  getTeacherResponse(teacherId: number): Observable<ITeacher> {
    return this.http.get<ITeacher>(TEACHER_API + '/' + teacherId);
  }

  postTeacher(teacher: Teacher): Observable<ITeacher>  {
    return this.http.post<ITeacher>(TEACHER_API, JSON.stringify(teacher), httpHeaders);
  }

  putTeacher(teacher: Teacher): Observable<ITeacher>  {
    return this.http.put<ITeacher>(TEACHER_API + '/' + teacher.id, JSON.stringify(teacher), httpHeaders);
  }

  deleteTeacher(teacher: ITeacher): Observable<ITeacher>  {
    return this.http.delete<ITeacher>(TEACHER_API + '/' + teacher.id);
  }
}
