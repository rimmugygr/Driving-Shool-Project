export class  User implements IUser{
  id: number;
  username: string;
  password: string;
  createDate: string;
  updateDate: string;

  constructor(id: number, username: string, password: string) {
    this.id = id;
    this.username = username;
    this.password = password;
  }
}

export interface IUser{
  id: number;
  username: string;
  password: string;
  createDate: string;
  updateDate: string;
}
