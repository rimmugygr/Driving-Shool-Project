import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProfilesTeachersPageComponent} from './profiles-teachers-page.component';

const routes: Routes = [
  {
    path: ``, component: ProfilesTeachersPageComponent
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfilesTeachersPageRoutingModule { }
