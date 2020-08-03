import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  template: `

    <mat-toolbar></mat-toolbar>
      <mat-list >
        <mat-list-item >
          <a routerLink="home" class="list-group-item list-group-item-action bg-light">
            <mat-icon>home</mat-icon><span >Home</span>
          </a>
        </mat-list-item>
        <mat-list-item >
          <a routerLink="student" class="list-group-item list-group-item-action bg-light">
            <mat-icon>school</mat-icon><span>Student</span>
          </a>
        </mat-list-item>
        <mat-list-item >
          <a routerLink="teacher" class="list-group-item list-group-item-action bg-light">
            <mat-icon>work</mat-icon><span>Teacher</span>
          </a>
        </mat-list-item>
        <mat-list-item >
          <a routerLink="home" class="list-group-item list-group-item-action bg-light">Home</a>
        </mat-list-item>
        <mat-list-item >
          <a routerLink="home" class="list-group-item list-group-item-action bg-light">Home</a>
        </mat-list-item>
        <mat-list-item >
          <a routerLink="home" class="list-group-item list-group-item-action bg-light">Home</a>
        </mat-list-item>
      </mat-list>

      <!--      <a routerLink="members" class="list-group-item list-group-item-action bg-light">Members</a>-->
      <!--      <a routerLink="posts" class="list-group-item list-group-item-action bg-light">Posts</a>-->
      <!--      <a routerLink="team" class="list-group-item list-group-item-action bg-light" *ngIf="auth.loggedIn">Profile</a>-->
      <!--      <a routerLink="races" class="list-group-item list-group-item-action bg-light" >Races</a>-->


  `
})
export class SidebarComponent {

  constructor() {
  }

}
