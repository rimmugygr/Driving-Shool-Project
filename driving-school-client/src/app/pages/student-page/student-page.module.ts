import { NgModule } from '@angular/core';

import { StudentPageRoutingModule } from './student-page-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {StudentPageComponent} from './student-page.component';
import {StudentEditModalComponent} from './components/student-edit-modal/student-edit-modal.component';
import {StudentDeleteModalComponent} from './components/student-delete-modal/student-delete-modal.component';
import {StudentAddModalComponent} from './components/student-add-modal/student-add-modal.component';


@NgModule({
  declarations: [
    StudentPageComponent,
    StudentEditModalComponent,
    StudentDeleteModalComponent,
    StudentAddModalComponent
  ],
  imports: [
    SharedModule,
    StudentPageRoutingModule
  ]
})
export class StudentPageModule { }
