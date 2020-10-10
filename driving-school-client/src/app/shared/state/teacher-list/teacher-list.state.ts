import { State, Action, Selector, StateContext } from '@ngxs/store';
import { TeacherListAction } from './teacher-list.actions';

export interface TeacherListStateModel {
  items: string[];
}

@State<TeacherListStateModel>({
  name: 'teacherList',
  defaults: {
    items: []
  }
})
export class TeacherListState {

  @Selector()
  public static getState(state: TeacherListStateModel) {
    return state;
  }

  @Action(TeacherListAction)
  public add(ctx: StateContext<TeacherListStateModel>, { payload }: TeacherListAction) {
    const stateModel = ctx.getState();
    stateModel.items = [...stateModel.items, payload];
    ctx.setState(stateModel);
  }
}
