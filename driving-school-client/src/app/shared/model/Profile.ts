import {IStudent} from './Student';
import {ITeacher} from './Teacher';

export class  Profile implements IProfile{
  profile: IStudent | ITeacher | null;
}

export interface IProfile{
  profile: IStudent | ITeacher | null;
}
