import { NgModule } from '@angular/core';

import { TeacherPageRoutingModule } from './teacher-page-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {TeacherPageComponent} from './teacher-page.component';
import {TeacherFormModalComponent} from './components/teacher-form-modal/teacher-form-modal.component';


@NgModule({
  declarations: [
    TeacherPageComponent,
    TeacherFormModalComponent,
  ],
  imports: [
    SharedModule,
    TeacherPageRoutingModule
  ]
})
export class TeacherPageModule { }
