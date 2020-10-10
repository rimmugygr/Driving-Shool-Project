import {IUser, User} from './User';

export class Student implements IStudent{
  public id: number;
  public firstName: string;
  public lastName: string;
  public categoryList: string[];
  public status: string;
  public hours: number;
  public address: string;
  public email: string;
  public phone: number;
  public createDate: string;
  public updateDate: string;
  public user: User;
}
export interface IStudent {
  id: number;
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
