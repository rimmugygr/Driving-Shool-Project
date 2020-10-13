import { State, Action, Selector, StateContext } from '@ngxs/store';
import {CreateStudent, DeleteStudent, FetchStudents, UpdateStudent} from './profiles-students-list.actions';
import {tap} from 'rxjs/operators';
import {StudentService} from '../../services/student.service';
import {IStudent} from '../../model/Student';
import {Injectable} from '@angular/core';

export interface ProfilesStudentsListStateModel {
  students: IStudent[];
}

@State<ProfilesStudentsListStateModel>({
  name: 'profilesStudentsList',
  defaults: {
    students: []
  }
})

@Injectable()
export class ProfilesStudentsListState {

  @Selector()
  public static getAllStudents(state: ProfilesStudentsListStateModel): IStudent[] {
    return state.students;
  }

  @Selector()
  public static getStudentById(state: ProfilesStudentsListStateModel): (id: string) => IStudent {
    return (id: string): IStudent => {
      return state.students.find(student => student.id === id);
    };
  }

  constructor(private studentService: StudentService) {
  }

  @Action(FetchStudents)
  public fetchStudents(ctx: StateContext<ProfilesStudentsListStateModel>): any {
    return this.studentService.getAllStudentsResponse().pipe(
      tap(data => {
        ctx.patchState({students: data});
      }),
    );
  }

  @Action(CreateStudent)
  public createStudent(ctx: StateContext<ProfilesStudentsListStateModel>, action: CreateStudent): any {
    return this.studentService.postStudent(action.payload.student).pipe(
      tap(data => {
        const students = [... ctx.getState().students, data];
        ctx.patchState({students});
      }),
    );
  }

  @Action(UpdateStudent)
  public updateTeacher(ctx: StateContext<ProfilesStudentsListStateModel>, action: UpdateStudent): any {
    return this.studentService.patchStudent(action.payload.student).pipe(
      tap(data => {
        const students = ctx.getState().students.map(x =>  x.id === data.id ? data : x);
        ctx.patchState({students});
      }),
    );
  }

  @Action(DeleteStudent)
  public deleteTeacher(ctx: StateContext<ProfilesStudentsListStateModel>, action: DeleteStudent): any {
    return this.studentService.deleteStudent(action.payload.student).pipe(
      tap(data => {
        const students = ctx.getState().students.filter(x =>  x.id !== data.id);
        ctx.patchState({students});
      }),
    );
  }
}
