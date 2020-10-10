import {ITeacher} from '../../model/Teacher';

export class FetchTeachers {
  public static readonly type = '[TeacherList]  Fetch all teachers';
}

export class CreateTeacher {
  public static readonly type = '[TeacherList] Create teacher';
  constructor(public payload: {teacher: ITeacher}) { }
}

export class UpdateTeacher {
  public static readonly type = '[TeacherList] Update teacher';
  constructor(public payload: {teacher: ITeacher}) { }
}
