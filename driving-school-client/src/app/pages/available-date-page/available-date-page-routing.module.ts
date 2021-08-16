import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AvailableDateViewPageComponent} from './available-date-view-page/available-date-view-page.component';
import {AvailableDateEditPageComponent} from './available-date-edit-page/available-date-edit-page.component';

const routes: Routes = [
  {
    path: 'edit', component: AvailableDateEditPageComponent,
    pathMatch: 'full',
  },
  {
    path: '', component: AvailableDateViewPageComponent,
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AvailableDatePageRoutingModule { }
