import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from '../auth/auth.service';

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
      <mat-list-item *ngIf="auth.showAdminBoard">
        <a routerLink="/student" class="list-group-item list-group-item-action bg-light">
          <mat-icon>school</mat-icon>
          <span>Student</span>
        </a>
      </mat-list-item>
      <mat-list-item *ngIf="auth.showAdminBoard">
        <a routerLink="/teacher" class="list-group-item list-group-item-action bg-light">
          <mat-icon>work</mat-icon>
          <span>Teacher</span>
        </a>
      </mat-list-item>
      <mat-list-item *ngIf="auth.showAdminBoard">
        <a routerLink="/available" class="list-group-item list-group-item-action bg-light">
          <mat-icon>work</mat-icon>
          <span>Available Date</span></a>
      </mat-list-item>
      <mat-list-item *ngIf="auth.showTeacherBoard">
        <a routerLink="tech" class="list-group-item list-group-item-action bg-light">
          <mat-icon>work</mat-icon>
          <span>Only Teacher</span></a>
      </mat-list-item>
      <mat-list-item *ngIf="auth.showStudentBoard">
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

  constructor(public auth: AuthService) {
  }

  ngOnInit(): void {
    this.auth.initProfile();
  }

}
