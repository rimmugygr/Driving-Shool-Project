import { NgModule } from '@angular/core';
import { AvailableDatePageRoutingModule } from './available-date-page-routing.module';
import {AvailableDateViewPageComponent} from './available-date-view-page/available-date-view-page.component';
import {SharedModule} from '../../shared/shared.module';
import {CalendarHeaderComponent} from './calendar-header/calendar-header.component';
import {AvailableDateEditPageComponent} from './available-date-edit-page/available-date-edit-page.component';


@NgModule({
  declarations: [
    AvailableDateViewPageComponent,
    AvailableDateEditPageComponent,
    CalendarHeaderComponent
  ],
  imports: [
    SharedModule,
    AvailableDatePageRoutingModule,
  ]
})
export class AvailableDatePageModule { }
