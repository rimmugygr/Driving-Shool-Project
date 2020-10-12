import { State, Action, Selector, StateContext } from '@ngxs/store';
import {CreateStudent, DeleteStudent, FetchStudents, UpdateStudent} from './students-list.actions';
import {tap} from 'rxjs/operators';
import {StudentService} from '../../services/student.service';
import {IStudent} from '../../model/Student';
import {Injectable} from '@angular/core';

export interface StudentsListStateModel {
  students: IStudent[];
}

@State<StudentsListStateModel>({
  name: 'studentsList',
  defaults: {
    students: []
  }
})

@Injectable()
export class StudentsListState {

  @Selector()
  public static getAllStudents(state: StudentsListStateModel): IStudent[] {
    return state.students;
  }

  @Selector()
  public static getStudentById(state: StudentsListStateModel): (id: string) => IStudent {
    return (id: string): IStudent => {
      return state.students.find(student => student.id === id);
    };
  }

  constructor(private studentService: StudentService) {
  }

  @Action(FetchStudents)
  public fetchStudents(ctx: StateContext<StudentsListStateModel>): any {
    return this.studentService.getAllStudentsResponse().pipe(
      tap(data => {
        ctx.patchState({students: data});
      }),
    );
  }

  @Action(CreateStudent)
  public createStudent(ctx: StateContext<StudentsListStateModel>, action: CreateStudent): any {
    return this.studentService.postStudent(action.payload.student).pipe(
      tap(data => {
        const students = [... ctx.getState().students, data];
        ctx.patchState({students});
      }),
    );
  }

  @Action(UpdateStudent)
  public updateTeacher(ctx: StateContext<StudentsListStateModel>, action: UpdateStudent): any {
    return this.studentService.patchStudent(action.payload.student).pipe(
      tap(data => {
        const students = ctx.getState().students.map(x =>  x.id === data.id ? data : x);
        ctx.patchState({students});
      }),
    );
  }

  @Action(DeleteStudent)
  public deleteTeacher(ctx: StateContext<StudentsListStateModel>, action: DeleteStudent): any {
    return this.studentService.deleteStudent(action.payload.student).pipe(
      tap(data => {
        const students = ctx.getState().students.filter(x =>  x.id !== data.id);
        ctx.patchState({students});
      }),
    );
  }
}
