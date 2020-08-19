import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AvailableDate} from '../model/AvailableDate';

@Injectable({
  providedIn: 'root'
})
export class AvailableService {
  link = '/server/api/teachers/available';
  linkList = '/server/api/teachers/available/list';
  linkWithTeacherId = `/server/api/teachers/{teacherId}/available`;

  constructor(private http: HttpClient) { }

  getAllAvailableDate(): Observable<any> {
    return this.http.get(this.link);
  }

  getAllAvailableDateByTeacherId(teacherId: number): Observable<any> {
    return this.http.get(this.linkWithTeacherId.replace('{teacherId}', String(teacherId)));
  }

  addAvailableDate(availableDate: AvailableDate): Observable<any>  {
    return this.http.post(
      this.link,
      JSON.stringify(availableDate),
      {headers : new HttpHeaders({'Content-Type': 'application/json'})}
    );
  }

  addAvailableDateList(availableDate: AvailableDate[]): Observable<any>  {
    return this.http.post(
      this.linkList,
      JSON.stringify(availableDate),
      {headers : new HttpHeaders({'Content-Type': 'application/json'})}
    );
  }

  deleteAvailableDate(availableDateId: number): Observable<any>  {
    return this.http.delete(this.link + '/' + availableDateId);
  }

  deleteAvailableDateList(availableDateId: number[]): Observable<any>  {
    return this.http.delete(this.linkList);
  }
  deleteAllAvailableDateByTeacherId(teacherId: number): Observable<any>  {
    return this.http.delete(this.linkWithTeacherId.replace('{teacherId}', String(teacherId)));
  }
}
