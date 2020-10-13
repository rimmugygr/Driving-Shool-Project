import { NgModule } from '@angular/core';

import { ProfilesStudentsPageRoutingModule } from './profiles-students-page-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {ProfilesStudentsPageComponent} from './profiles-students-page.component';
import {ProfilesStudentsFormModalComponent} from './components/profiles-students-form-modal/profiles-students-form-modal.component';


@NgModule({
  declarations: [
    ProfilesStudentsPageComponent,
    ProfilesStudentsFormModalComponent
  ],
  imports: [
    SharedModule,
    ProfilesStudentsPageRoutingModule
  ]
})
export class ProfilesStudentsPageModule { }
