import { NgModule } from '@angular/core';

import { ProfilesTeachersPageRoutingModule } from './profiles-teachers-page-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {ProfilesTeachersPageComponent} from './profiles-teachers-page.component';
import {ProfilesTeachersFormModalComponent} from './components/profiles-teachers-form-modal/profiles-teachers-form-modal.component';


@NgModule({
  declarations: [
    ProfilesTeachersPageComponent,
    ProfilesTeachersFormModalComponent,
  ],
  imports: [
    SharedModule,
    ProfilesTeachersPageRoutingModule
  ]
})
export class ProfilesTeachersPageModule { }
