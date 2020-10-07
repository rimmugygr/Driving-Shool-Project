import { NgModule } from '@angular/core';

import { TeacherPageRoutingModule } from './teacher-page-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {TeacherPageComponent} from './teacher-page.component';
import {TeacherEditModalComponent} from './components/teacher-edit-modal/teacher-edit-modal.component';
import {TeacherDeleteModalComponent} from './components/teacher-delete-modal/teacher-delete-modal.component';
import {TeacherAddModalComponent} from './components/teacher-add-modal/teacher-add-modal.component';


@NgModule({
  declarations: [
    TeacherPageComponent,
    TeacherEditModalComponent,
    TeacherDeleteModalComponent,
    TeacherAddModalComponent,],
  imports: [
    SharedModule,
    TeacherPageRoutingModule
  ]
})
export class TeacherPageModule { }
