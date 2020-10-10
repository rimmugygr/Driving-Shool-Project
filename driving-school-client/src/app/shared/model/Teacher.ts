import {IUser, User} from './User';

export class  Teacher implements ITeacher{
  firstName: string;
  lastName: string;
  categoryList: string[];
  createDate: string;
  updateDate: string;
  user: User;
  id: string;
}

export interface  ITeacher{
  firstName: string;
  lastName: string;
  categoryList: string[];
  createDate: string;
  updateDate: string;
  user: IUser;
  id: string;
}
