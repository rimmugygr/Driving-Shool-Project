import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {StudentListComponent} from './pages/student-list/student-list.component';
import {TeacherListComponent} from './pages/teacher-list/teacher-list.component';
import {AvailableDateComponent} from './pages/available-date/available-date.component';
import {AuthGuard} from './services/auth/auth.guard';
import {LoginModalComponent} from './pages/commons/login-modal/login-modal.component';



const routes: Routes = [
  {
    path: 'student',
    component: StudentListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'teacher',
    component: TeacherListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'available',
    component: AvailableDateComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'tech',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'stud',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: '**',
    component: HomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
