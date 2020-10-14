import {IUser, User} from './User';

export class  Teacher implements ITeacher{
  id: string;
  firstName: string;
  lastName: string;
  createDate: string;
  updateDate: string;
  user: User;
  categoryList?: string[];
}

export interface  ITeacher{
  id: string;
  firstName: string;
  lastName: string;
  createDate: string;
  updateDate: string;
  user: IUser;
  categoryList?: string[];
}
