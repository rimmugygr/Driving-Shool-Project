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
          <a routerLink="available" class="list-group-item list-group-item-action bg-light">
            <mat-icon>work</mat-icon><span>Available Date</span></a>
        </mat-list-item>
        <mat-list-item >
          <a routerLink="home" class="list-group-item list-group-item-action bg-light">Home</a>
        </mat-list-item>
        <mat-list-item >
          <a routerLink="home" class="list-group-item list-group-item-action bg-light">Home</a>
        </mat-list-item>
      </mat-list>

  `
})
export class SidebarComponent {

  constructor() {
  }

}
