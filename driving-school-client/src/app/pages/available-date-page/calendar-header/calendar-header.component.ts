import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CalendarView } from 'angular-calendar';

@Component({
  selector: 'app-calendar-header',
  templateUrl: './calendar-header.component.html',
  styleUrls: ['./calendar-header.component.css']
})
export class CalendarHeaderComponent {
  @Input() view: CalendarView;


  @Input() viewDate: Date;

  @Input() locale = 'en';

  @Output() viewChange = new EventEmitter<CalendarView>();

  @Output() viewDateChange = new EventEmitter<Date>();

  CalendarView = CalendarView;
}
