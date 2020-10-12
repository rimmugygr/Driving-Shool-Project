import {IUser, User} from './User';

export class Student implements IStudent{
  id: string;
  firstName: string;
  lastName: string;
  categoryList: string[];
  status: string;
  hours: number;
  address: string;
  email: string;
  phone: number;
  createDate: string;
  updateDate: string;
  user: User;
}
export interface IStudent {
  id: string;
  firstName: string;
  lastName: string;
  categoryList: string[];
  status: string;
  hours: number;
  address: string;
  email: string;
  phone: number;
  createDate: string;
  updateDate: string;
  user: IUser;
}
