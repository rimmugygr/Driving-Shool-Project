import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AvailableDate, IAvailableDate} from '../model/AvailableDate';
import {tap} from 'rxjs/operators';

const AVAILABLE_API = '/server/api/teachers/available';
const AVAILABLE_API_WITH_TEACHER_ID = `/server/api/teachers/{teacherId}/available`;
const httpHeaders = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AvailableService {

  constructor(private http: HttpClient) { }

  getAllAvailableDate(): Observable<IAvailableDate[]> {
    return this.http.get<IAvailableDate[]>(AVAILABLE_API).pipe(
      tap(x => console.log(JSON.stringify(x[0])))
    );
  }

  getAllAvailableDateByTeacherId(teacherId: number): Observable<any> {
    return this.http.get(AVAILABLE_API_WITH_TEACHER_ID.replace('{teacherId}', String(teacherId)));
  }

  addAvailableDate(availableDate: AvailableDate): Observable<any>  {
    return this.http.post(
      AVAILABLE_API,
      JSON.stringify(availableDate),
      httpHeaders
    );
  }

  addAvailableDateList(availableDate: AvailableDate[]): Observable<any>  {
    return this.http.post(
      AVAILABLE_API + `/list`,
      JSON.stringify(availableDate),
      httpHeaders
    );
  }

  deleteAvailableDate(availableDateId: number): Observable<any>  {
    return this.http.delete(AVAILABLE_API + '/' + availableDateId);
  }

  deleteAvailableDateList(availableDateId: number[]): Observable<any>  {
    return this.http.delete(AVAILABLE_API + `/list`);
  }
  deleteAllAvailableDateByTeacherId(teacherId: number): Observable<any>  {
    return this.http.delete(AVAILABLE_API_WITH_TEACHER_ID.replace('{teacherId}', String(teacherId)));
  }
}
