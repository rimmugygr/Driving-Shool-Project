import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AvailableDatePageComponent} from './available-date-page.component';

const routes: Routes = [
  {
    path: ``, component: AvailableDatePageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AvailableDatePageRoutingModule { }
