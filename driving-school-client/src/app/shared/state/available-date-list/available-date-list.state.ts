import { State, Action, Selector, StateContext } from '@ngxs/store';
import {FetchAvailableDates} from './available-date-list.actions';
import {IAvailableDate} from '../../model/AvailableDate';
import {AvailableService} from '../../services/available.service';
import {tap} from 'rxjs/operators';
import {Injectable} from '@angular/core';

export interface AvailableDateListStateModel {
  availableDates: IAvailableDate[];
}

@State<AvailableDateListStateModel>({
  name: 'availableDateList',
  defaults: {
    availableDates: []
  }
})
@Injectable()
export class AvailableDateListState {

  @Selector()
  public static getAvailableDates(state: AvailableDateListStateModel): IAvailableDate[] {
    return state.availableDates;
  }

  constructor(private availableService: AvailableService) {
  }

  @Action(FetchAvailableDates)
  public fetchAvailableDates(ctx: StateContext<AvailableDateListStateModel>): any {
    return this.availableService.getAllAvailableDate().pipe(
      tap(data => {
        ctx.patchState({availableDates: data});
      })
    );
  }
}
