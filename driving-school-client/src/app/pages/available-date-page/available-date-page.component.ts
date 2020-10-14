import {ChangeDetectionStrategy, Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {IAvailableDate} from '../../shared/model/AvailableDate';
import {Select, Store} from '@ngxs/store';
import {FetchAvailableDates} from '../../shared/state/available-date-list/available-date-list.actions';
import {CalendarEventAction, CalendarEventTimesChangedEvent, CalendarEvent, CalendarView} from 'angular-calendar';
import {Observable, Subject} from 'rxjs';
import {startOfDay, endOfDay, subDays, addDays, endOfMonth, isSameDay, isSameMonth, addHours, startOfMonth, startOfWeek, endOfWeek} from 'date-fns';
import {AvailableDateListState} from '../../shared/state/available-date-list/available-date-list.state';
import {map, tap} from 'rxjs/operators';
import { IColor} from '../../shared/model/Colors';


const colors: IColor[] = [
  { primary: '#ad2121', secondary: '#eca0a0' },
  { primary: '#1e90ff', secondary: '#7db1e5' },
  { primary: '#e3bc08', secondary: '#ecda86' },
  { primary: '#902394', secondary: '#cea9cf' }
];


@Component({
  selector: 'app-available-date',
  templateUrl: './available-date-page.component.html',
  styleUrls: ['./available-date-page.component.css']
})
export class AvailableDatePageComponent implements OnInit {
  placeName = `Available Date List`;

  view: CalendarView = CalendarView.Month;
  viewDate: Date = new Date();
  events$: Observable<CalendarEvent<{ availableDate: IAvailableDate }>[]>;
  activeDayIsOpen = false;

  @Select(AvailableDateListState.getAvailableDates)
  students$: Observable<IAvailableDate[]>;

  constructor(private store: Store) {


    this.events$ = this.students$.pipe(
      tap(availableDates => { // color assign
        const tabId = [...new Set(availableDates.map(x => x.teacherId))].sort();
        colors.forEach(c => c.id = tabId.shift());
      }),
      map(availableDates => {
        return availableDates.map(availableDate => {
          const dateStrings = availableDate.date.split(RegExp(`[- :]`)).map(x => Number(x)); // get table of date y,m,d,h,m,s
          // month 0-11 not 1-12 as java
          const start = new Date( dateStrings[0], dateStrings[1] - 1,  dateStrings[2], dateStrings[3], dateStrings[4]); // start date
          const end = new Date( dateStrings[0], dateStrings[1] - 1,  dateStrings[2], dateStrings[3] + 2, dateStrings[4]); // end date
          const title = availableDate.teacherFirstName + ' ' + availableDate.teacherLastName + ': '
            + dateStrings[3] + '-' +  (dateStrings[3] + 2); // create title
          const color = colors.find(c => c.id === availableDate.teacherId); // find color which is assign by id
          return { title, start, end, meta: { availableDate }, color };
        });
      }),
      tap(x => console.log(JSON.stringify(x))),
    );
  }

  ngOnInit(): void {
    this.getAvailableDate();
  }

  getAvailableDate(): void {
    this.store.dispatch(new FetchAvailableDates());
  }

  refreshList(): void {
    this.getAvailableDate();
  }

  changeDay( date ): void {
    this.viewDate = date;
    this.view = CalendarView.Day;
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent<{ availableBate: IAvailableDate }>[]; }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
        this.viewDate = date;
        this.view = CalendarView.Week;
      } else {
        this.activeDayIsOpen = true;
        this.viewDate = date;
      }
    }
  }

  eventClicked(event: CalendarEvent<{ availableBate: IAvailableDate }>): void {

  }



}
