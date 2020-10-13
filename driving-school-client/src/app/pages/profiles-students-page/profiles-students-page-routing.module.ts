import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProfilesStudentsPageComponent} from './profiles-students-page.component';

const routes: Routes = [
  {
    path: ``, component: ProfilesStudentsPageComponent
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfilesStudentsPageRoutingModule { }
