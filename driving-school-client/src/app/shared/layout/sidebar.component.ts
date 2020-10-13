import {Component, OnInit} from '@angular/core';
import {Select} from '@ngxs/store';
import {UserAuthState} from '../state/user-auth/user-auth.state';
import {Observable, of} from 'rxjs';

interface NavigationItem {
  icon: string;
  name: string;
  type: 'page' | 'action';
  isVisible: Observable<boolean> | boolean;
  url?: string;
}

@Component({
  selector: 'app-sidebar',
  template: `

    <mat-toolbar></mat-toolbar>
    <mat-list>
      <div *ngFor="let item of navItems">
        <mat-list-item *ngIf="item.isVisible | async">
          <a routerLink="{{item.url}}" class="list-group-item list-group-item-action bg-light">
            <div><mat-icon >{{item.icon}}</mat-icon>{{item.name}}</div>
          </a>
        </mat-list-item>
      </div>
    </mat-list>
 `
})


export class SidebarComponent implements OnInit{
  navItems: NavigationItem[];
  isAlwaysTrue: Observable<boolean> = of(true);
  @Select(UserAuthState.isAuthenticated) isAuthenticated$: Observable<boolean>;
  @Select(UserAuthState.isShowAdminBoard) isShowAdminBoard$: Observable<boolean>;
  @Select(UserAuthState.isShowStudentBoard) isShowStudentBoard$: Observable<boolean>;
  @Select(UserAuthState.isShowTeacherBoard) isShowTeacherBoard$: Observable<boolean>;

  constructor() {
    this.navItems = [
      { icon: `home`, name: 'Home', type: 'page', url: '/home', isVisible: this.isAlwaysTrue},
      { icon: `school`, name: 'Profiles Student', type: 'page', url: '/student', isVisible: this.isShowAdminBoard$},
      { icon: `work`, name: 'Profiles Teacher', type: 'page', url: '/teacher', isVisible: this.isShowAdminBoard$},
      { icon: `work`, name: 'Available Date', type: 'page', url: '/available', isVisible: this.isShowAdminBoard$},
      { icon: `person`, name: 'Profile', type: 'page', url: '/profile', isVisible: this.isAuthenticated$},
      { icon: `work`, name: 'Only Teacher', type: 'page', url: '/tech', isVisible: this.isShowTeacherBoard$},
      { icon: `work`, name: 'Only Student', type: 'page', url: '/stud', isVisible: this.isShowStudentBoard$},
      { icon: `work`, name: 'Login', type: 'page', url: '/login', isVisible: this.isAlwaysTrue},
    ];
  }

  ngOnInit(): void {
  }

}
