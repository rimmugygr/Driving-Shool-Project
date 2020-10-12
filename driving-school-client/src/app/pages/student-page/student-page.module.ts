import { NgModule } from '@angular/core';

import { StudentPageRoutingModule } from './student-page-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {StudentPageComponent} from './student-page.component';
import {StudentFormModalComponent} from './components/student-form-modal/student-form-modal.component';


@NgModule({
  declarations: [
    StudentPageComponent,
    StudentFormModalComponent
  ],
  imports: [
    SharedModule,
    StudentPageRoutingModule
  ]
})
export class StudentPageModule { }
