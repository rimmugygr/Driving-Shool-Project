import {IStudent} from '../../model/Student';

export class FetchStudents {
  public static readonly type = '[StudentsList]  Fetch all students';
}

export class CreateStudent {
  public static readonly type = '[StudentsList] Create student';
  constructor(public payload: {student: IStudent}) { }
}
