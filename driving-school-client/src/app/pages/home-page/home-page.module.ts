import { NgModule } from '@angular/core';

import { HomePageRoutingModule } from './home-page-routing.module';
import {HomePageComponent} from './home-page.component';
import {SharedModule} from '../../shared/shared.module';


@NgModule({
  declarations: [HomePageComponent],
  imports: [
    SharedModule,
    HomePageRoutingModule
  ]
})
export class HomePageModule { }
