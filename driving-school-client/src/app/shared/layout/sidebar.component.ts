import {Component, OnInit} from '@angular/core';
import {Select} from '@ngxs/store';
import {UserAuthState} from '../state/user-auth/user-auth.state';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-sidebar',
  template: `

    <mat-toolbar></mat-toolbar>
    <mat-list>
      <mat-list-item>
        <a routerLink="/home" class="list-group-item list-group-item-action bg-light">
          <mat-icon>home</mat-icon>
          <span>Home</span>
        </a>
      </mat-list-item>
      <mat-list-item *ngIf="isShowAdminBoard$ | async">
        <a routerLink="/student" class="list-group-item list-group-item-action bg-light">
          <mat-icon>school</mat-icon>
          <span>Student</span>
        </a>
      </mat-list-item>
      <mat-list-item *ngIf="isShowAdminBoard$ | async">
        <a routerLink="/teacher" class="list-group-item list-group-item-action bg-light">
          <mat-icon>work</mat-icon>
          <span>Teacher</span>
        </a>
      </mat-list-item>
      <mat-list-item *ngIf="isShowAdminBoard$ | async">
        <a routerLink="/available" class="list-group-item list-group-item-action bg-light">
          <mat-icon>work</mat-icon>
          <span>Available Date</span></a>
      </mat-list-item>
      <mat-list-item *ngIf="isAuthenticated$ | async">
        <a routerLink="/profile" class="list-group-item list-group-item-action bg-light">
          <mat-icon>person</mat-icon>
          <span>Profile</span></a>
      </mat-list-item>
      <mat-list-item *ngIf="isShowTeacherBoard$ | async">
        <a routerLink="tech" class="list-group-item list-group-item-action bg-light">
          <mat-icon>work</mat-icon>
          <span>Only Teacher</span></a>
      </mat-list-item>
      <mat-list-item *ngIf="isShowStudentBoard$ | async">
        <a routerLink="stud" class="list-group-item list-group-item-action bg-light">
          <mat-icon>work</mat-icon>
          <span>Only Student</span></a>
      </mat-list-item>
      <mat-list-item>
        <a routerLink="login" class="list-group-item list-group-item-action bg-light">
          <mat-icon>work</mat-icon>
          <span>Login</span></a>
      </mat-list-item>
    </mat-list>

  `
})
export class SidebarComponent implements OnInit{

  @Select(UserAuthState.isAuthenticated)
  isAuthenticated$: Observable<boolean>;

  @Select(UserAuthState.isShowAdminBoard)
  isShowAdminBoard$: Observable<boolean>;

  @Select(UserAuthState.isShowStudentBoard)
  isShowStudentBoard$: Observable<boolean>;

  @Select(UserAuthState.isShowTeacherBoard)
  isShowTeacherBoard$: Observable<boolean>;

  constructor() {
  }

  ngOnInit(): void {
  }

}
