import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TeacherPageComponent} from './teacher-page.component';

const routes: Routes = [
  {
    path: ``, component: TeacherPageComponent
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherPageRoutingModule { }
