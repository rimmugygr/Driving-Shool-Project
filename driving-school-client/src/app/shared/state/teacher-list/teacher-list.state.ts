import { State, Action, Selector, StateContext } from '@ngxs/store';
import {CreateTeacher, FetchTeachers, UpdateTeacher} from './teacher-list.actions';
import {ITeacher} from '../../model/Teacher';
import {Injectable} from '@angular/core';
import {tap} from 'rxjs/operators';
import {TeacherService} from '../../services/teacher.service';

export interface TeacherListStateModel {
  teachers: ITeacher[];
}

@State<TeacherListStateModel>({
  name: 'teacherList',
  defaults: {
    teachers: []
  }
})
@Injectable()
export class TeacherListState {

  @Selector()
  public static getAllTeachers(state: TeacherListStateModel): ITeacher[] {
    return state.teachers;
  }

  @Selector()
  public static getTeacherById(state: TeacherListStateModel): (id: string) => ITeacher {
    return (id: string): ITeacher => {
      return state.teachers.find(employee => employee.id === id);
    };
  }

  constructor(private teacherService: TeacherService) {
  }

  @Action(FetchTeachers)
  public fetchTeachers(ctx: StateContext<TeacherListStateModel>): any {
    return this.teacherService.getAllTeachersResponse().pipe(
      tap(data => {
        ctx.patchState({teachers: data});
      }),
    );
  }

  @Action(CreateTeacher)
  public createTeacher(ctx: StateContext<TeacherListStateModel>, action: CreateTeacher): any {
    console.log(JSON.stringify(action.payload.teacher));
    return this.teacherService.postTeacher(action.payload.teacher).pipe(
      tap(data => {
        const teachers = [... ctx.getState().teachers, data];
        ctx.patchState({teachers});
      }),
    );
  }

  @Action(UpdateTeacher)
  public updateTeacher(ctx: StateContext<TeacherListStateModel>, action: UpdateTeacher): any {
    return this.teacherService.putTeacher(action.payload.teacher).pipe(
      tap(data => {
        const teachers = [... ctx.getState().teachers, data];
        ctx.patchState({teachers});
      }),
    );
  }
}
