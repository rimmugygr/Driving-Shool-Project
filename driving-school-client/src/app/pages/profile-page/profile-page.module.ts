import { NgModule } from '@angular/core';

import { ProfilePageRoutingModule } from './profile-page-routing.module';
import {ProfilePageComponent} from './profile-page.component';
import {SharedModule} from '../../shared/shared.module';


@NgModule({
  declarations: [ProfilePageComponent],
  imports: [
    SharedModule,
    ProfilePageRoutingModule
  ]
})
export class ProfilePageModule { }
