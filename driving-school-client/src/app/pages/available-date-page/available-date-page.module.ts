import { NgModule } from '@angular/core';
import { AvailableDatePageRoutingModule } from './available-date-page-routing.module';
import {AvailableDatePageComponent} from './available-date-page.component';
import {SharedModule} from '../../shared/shared.module';
import {CalendarHeaderComponent} from './calendar-header/calendar-header.component';


@NgModule({
  declarations: [AvailableDatePageComponent, CalendarHeaderComponent],
    imports: [
      SharedModule,
      AvailableDatePageRoutingModule,
    ]
})
export class AvailableDatePageModule { }
