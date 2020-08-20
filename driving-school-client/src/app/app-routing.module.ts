import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {StudentListComponent} from './pages/student-list/student-list.component';
import {TeacherListComponent} from './pages/teacher-list/teacher-list.component';
import {AvailableDateComponent} from './pages/available-date/available-date.component';



const routes: Routes = [
  {
    path: 'student',
    component: StudentListComponent
  },
  {
    path: 'teacher',
    component: TeacherListComponent
  },
  {
    path: 'available',
    component: AvailableDateComponent
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
