import {IUser, User} from './User';

export class Student implements IStudent{
  id: string;
  firstName: string;
  lastName: string;
  status: string;
  hours: number;
  address: string;
  email: string;
  phone: number;
  createDate: string;
  updateDate: string;
  user: User;
  categoryList?: string[];
}
export interface IStudent {
  id: string;
  firstName: string;
  lastName: string;
  status: string;
  hours: number;
  address: string;
  email: string;
  phone: number;
  createDate: string;
  updateDate: string;
  user: IUser;
  categoryList?: string[];
}
