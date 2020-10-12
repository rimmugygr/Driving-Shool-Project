import {IStudent} from '../../model/Student';

export class FetchStudents {
  public static readonly type = '[StudentsList]  Fetch all students';
}

export class CreateStudent {
  public static readonly type = '[StudentsList] Create student';
  constructor(public payload: {student: IStudent}) { }
}

export class UpdateStudent {
  public static readonly type = '[StudentsList] Update student';
  constructor(public payload: {student: IStudent}) { }
}

export class DeleteStudent {
  public static readonly type = '[TeacherList] Delete student';
  constructor(public payload: {student: IStudent}) { }
}
