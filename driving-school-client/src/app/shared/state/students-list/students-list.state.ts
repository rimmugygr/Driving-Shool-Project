import { State, Action, Selector, StateContext } from '@ngxs/store';
import { StudentsListAction } from './students-list.actions';

export interface StudentsListStateModel {
  items: string[];
}

@State<StudentsListStateModel>({
  name: 'studentsList',
  defaults: {
    items: []
  }
})
export class StudentsListState {

  @Selector()
  public static getState(state: StudentsListStateModel) {
    return state;
  }

  @Action(StudentsListAction)
  public add(ctx: StateContext<StudentsListStateModel>, { payload }: StudentsListAction) {
    const stateModel = ctx.getState();
    stateModel.items = [...stateModel.items, payload];
    ctx.setState(stateModel);
  }
}
